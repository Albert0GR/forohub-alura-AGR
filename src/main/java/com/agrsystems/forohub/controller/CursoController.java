package com.agrsystems.forohub.controller;

import com.agrsystems.forohub.dto.curso.DatosListadoCurso;
import com.agrsystems.forohub.dto.topico.DatosListadoTopico;
import com.agrsystems.forohub.dto.curso.DatosListadoCurso;
import com.agrsystems.forohub.model.Curso;
import com.agrsystems.forohub.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public Curso crearCurso(@Valid @RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    @GetMapping
    public Page<DatosListadoCurso> listarCursos(@PageableDefault(size = 10) Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(DatosListadoCurso::new);
    }
}
