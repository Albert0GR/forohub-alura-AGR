package com.agrsystems.forohub.dto.topico;



import com.agrsystems.forohub.dto.curso.DatosCurso;
import com.agrsystems.forohub.dto.curso.DatosListadoCurso;
import com.agrsystems.forohub.dto.usuario.DatosUsuario;
import com.agrsystems.forohub.model.Topico;

import java.time.LocalDateTime;

public record DatosRegistroTopico(

        String titulo,
        String mensaje,
        String status,
        DatosCurso curso,
        DatosUsuario nombre
        ) {


}
