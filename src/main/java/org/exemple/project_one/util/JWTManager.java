package org.exemple.project_one.util;

import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.OctetKeyPair;
import com.nimbusds.jose.jwk.gen.OctetKeyPairGenerator;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Singleton;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.exemple.project_one.controllers.Role;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Singleton
@LocalBean
public class JWTManager {
    private final Config config= ConfigProvider.getConfig();
    private final Map<String,Long> keyPairExpirationTime = new HashMap<>();
    private final Set<OctetKeyPair> cachedKeyPairs = new HashSet<>();
    private final OctetKeyPairGenerator keyPairGenerator = new OctetKeyPairGenerator(Curve.Ed25519);
    public String generateJWT(String username, Role[] roles) {
        return null;
    }
}
