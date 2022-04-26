package org.exemple.project_one.boundaries;


import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.exemple.project_one.controllers.UserManager;
import org.exemple.project_one.entities.User;
import org.exemple.project_one.util.JWTManager;

import java.util.logging.Logger;

@Path("/")
public class AuthenticationEndpoint {
    @Inject
    private Logger log;
    @EJB
    private UserManager userManager;
    @EJB
    private JWTManager jwtManager;
    @POST
    @Path("/oauth/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticate(@FormParam("username") String username,
                                 @FormParam("password") String password){
        try{
            User user = userManager.authenticate(username,password);
            if(user==null){
                throw new WebApplicationException(Response.Status.UNAUTHORIZED);
            }
            return Response.ok().entity("{\"accessToken:\""+
                    jwtManager.generateJWT(username,userManager.getRoles(user))+"\"}"
                    ).build();

        }catch( EJBException e ){
            log.warning(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).
                    header(HttpHeaders.WWW_AUTHENTICATE,"OAuth Bearer thingsDb").
                    build();
        }
    }


}
