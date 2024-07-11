package com.agrsystems.forohub.controller;

import com.agrsystems.forohub.model.Topico;
import com.agrsystems.forohub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }
}
