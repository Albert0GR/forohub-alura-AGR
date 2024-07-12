package com.agrsystems.forohub.dto.curso;

import com.agrsystems.forohub.model.Curso;

public record DatosListadoCurso(
        Long id,
        String nombre,
        String categoria) {

    public DatosListadoCurso(Curso curso) {
        this(curso.getId(),
                curso.getNombre(),
                curso.getCategoria().toString());
    }
}
