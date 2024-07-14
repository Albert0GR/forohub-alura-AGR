package com.agrsystems.forohub.controller;

import com.agrsystems.forohub.dto.usuario.DatosAutenticacionUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    //@Autowired
    //private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.nombre(),
                datosAutenticacionUsuario.contrasena());
        //var usuarioAutenticado =authenticationManager.authenticate(authToken);
        //var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        //return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
        authenticationManager.authenticate(authToken);
        return ResponseEntity.ok().build();
    }

}
