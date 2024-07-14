package com.agrsystems.forohub.infra.security;

import com.agrsystems.forohub.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//define un componente
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //System.out.println("el filtro esta siendo llamado");
        //obtener el token del header
        var authHeader = request.getHeader("Authorization");
        //System.out.println(token);
        if (authHeader != null){
            //System.out.println("token no es null");
            var token = authHeader.replace("Bearer ","");
            //System.out.println(token);
            //devuelve el subject del token
            //System.out.println(tokenService.getSubject(token));
            var subject = tokenService.getSubject(token);
            if (subject != null){
                //token ya esta valido
                var usuario = usuarioRepository.findByNombre(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario,null,
                        usuario.getAuthorities()); // forzamos un inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request,response);
    }
}
