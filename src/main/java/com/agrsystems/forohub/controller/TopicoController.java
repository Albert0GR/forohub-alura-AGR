package com.agrsystems.forohub.controller;

import com.agrsystems.forohub.dto.topico.DatosListadoTopico;
import com.agrsystems.forohub.dto.topico.DatosListadoTopico;
import com.agrsystems.forohub.model.Topico;
import com.agrsystems.forohub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public Topico crearTopico(@Valid @RequestBody Topico topico) {
        return topicoRepository.save(topico);
    }

    @GetMapping
    public Page<DatosListadoTopico> listarTopicos(@PageableDefault(size = 10,sort="fechaCreacion") Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
    }
}
