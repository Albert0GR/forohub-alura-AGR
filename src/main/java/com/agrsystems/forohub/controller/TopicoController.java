package com.agrsystems.forohub.controller;

import com.agrsystems.forohub.dto.topico.DatosDetalleTopico;
import com.agrsystems.forohub.dto.topico.DatosListadoTopico;
import com.agrsystems.forohub.dto.topico.DatosListadoTopico;
import com.agrsystems.forohub.model.Topico;
import com.agrsystems.forohub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Page<DatosListadoTopico> listarTopicos(@PageableDefault(size = 30,sort="fechaCreacion") Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalleTopico(@PathVariable Long id) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico no encontrado");
        }
        return ResponseEntity.ok(new DatosDetalleTopico(topicoOpt.get()));
    }
}
