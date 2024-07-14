package com.agrsystems.forohub.controller;

import com.agrsystems.forohub.dto.topico.DatosActualizarTopico;
import com.agrsystems.forohub.dto.topico.DatosDetalleTopico;
import com.agrsystems.forohub.dto.topico.DatosListadoTopico;
import com.agrsystems.forohub.dto.topico.DatosListadoTopico;
import com.agrsystems.forohub.model.Curso;
import com.agrsystems.forohub.model.Topico;
import com.agrsystems.forohub.model.Usuario;
import com.agrsystems.forohub.repository.CursoRepository;
import com.agrsystems.forohub.repository.TopicoRepository;
import com.agrsystems.forohub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

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

   /* @PutMapping("/{id}")
    @Transactional
    public void actualizarTopico(@PathVariable Long id, @RequestBody DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatos(datosActualizarTopico);
    }*/
   @PutMapping("/{id}")
   @Transactional
   public ResponseEntity<?> actualizarTopico(@PathVariable Long id, @RequestBody DatosActualizarTopico datosActualizarTopico) {
       Optional<Topico> optionalTopico = topicoRepository.findById(id);
       if (optionalTopico.isPresent()) {
           Topico topico = optionalTopico.get();
           topico.actualizarDatos(datosActualizarTopico);
           topicoRepository.save(topico);
           return ResponseEntity.ok().build();
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Topico no encontrado");
       }
   }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Topico no encontrado");
        }
    }


}
