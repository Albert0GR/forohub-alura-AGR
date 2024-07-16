package com.agrsystems.forohub.dto.usuario;

import com.agrsystems.forohub.model.Usuario;

public record DatosListadoUsuario(
        Long id,
        String nombre,
        String correoElectronico) {

    public DatosListadoUsuario(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico()
        );
    }
}
