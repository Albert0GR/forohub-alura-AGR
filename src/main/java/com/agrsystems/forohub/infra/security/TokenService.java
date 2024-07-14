package com.agrsystems.forohub.infra.security;

import com.agrsystems.forohub.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(/*Usuario usuario*/){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject("alberto1")
                    //.withSubject(usuario.getLogin())
                    //.withClaim("id",usuario.getId())
                    //.withExpiresAt(generarFechaExpiracion(2))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }
}
