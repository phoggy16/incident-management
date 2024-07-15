package com.incident.incident_management.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTUtil {
    @Value("${jwt.secret.token}")
    private String secretKey;

    public String generateJWTToken(String username, String requestURL, Date expiresAt){
        Algorithm algorithm=JWTAlgorithm();
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(expiresAt)
                .withIssuer(requestURL)
                .sign(algorithm);
    }

    public Algorithm JWTAlgorithm(){
        return Algorithm.HMAC256(secretKey.getBytes());
    }
}
