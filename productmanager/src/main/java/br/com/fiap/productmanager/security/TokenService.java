package br.com.fiap.productmanager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    public String getSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(Exception e) {
            log.warn(e);
            return null;
        }
    }

    public String getClaim(String token, String claim) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getClaim(claim)
                    .asString();
        } catch(Exception e) {
            log.warn(e);
            return null;
        }
    }


}
