package org.exemple.project_one.boundaries;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.websocket.CloseReason;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.exemple.project_one.controllers.MessageEventManager;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@ServerEndpoint(value = "/pushes",encoders = {PushWebSocketEndpoint.JsonTextEncoder.class},
        decoders = {PushWebSocketEndpoint.JsonTextDecoder.class})
public class PushWebSocketEndpoint {
    @Inject
    private Logger log;
    @EJB
    private MessageEventManager messageEventManager;
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    public static void broadcastMessage(JsonObject message){
        for(Session session: sessions){
            try {
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @OnOpen
    public void onOpen(Session session){
        log.info("Push WebSocket Opened: "+session.getId());
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        log.info("Push WebSocket closed for "+session.getId()
                +" with reason ["+closeReason.getCloseCode()+":"+closeReason.getReasonPhrase()+"]");
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        log.warning("Push WebSocket error for "+session.getId()+": "+ throwable.getMessage());
    }

    @OnMessage
    public void onMessage(JsonObject message,Session session){
        if(session.isOpen() && session.isSecure()) {
            messageEventManager.publishFromClient(message);
        }
    }
    static final class JsonTextEncoder implements Encoder.Text<JsonObject>{

        @Override
        public String encode(JsonObject object) throws EncodeException {
            return object.toString();
        }
    }
    static final class JsonTextDecoder implements Decoder.Text<JsonObject>{

        @Override
        public JsonObject decode(String s) throws DecodeException {
            try(JsonReader jsonReader= Json.createReader(new StringReader(s))){
                return jsonReader.readObject();
            }
        }

        @Override
        public boolean willDecode(String s) {
            try(JsonReader jsonReader= Json.createReader(new StringReader(s))){
                jsonReader.readObject();
                return true;
            }catch (JsonException e){
                return false;
            }
        }
    }

}
