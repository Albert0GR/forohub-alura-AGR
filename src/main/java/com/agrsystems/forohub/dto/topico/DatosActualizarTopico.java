package com.agrsystems.forohub.dto.topico;



import com.agrsystems.forohub.model.Topico;

import java.time.LocalDateTime;

public record DatosActualizarTopico(

        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status) {

    public DatosActualizarTopico(Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus());
    }
}
