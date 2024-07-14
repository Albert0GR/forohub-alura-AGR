package com.agrsystems.forohub.controller;

import com.agrsystems.forohub.dto.topico.*;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

//    @PostMapping
//    public Topico crearTopico(@Valid @RequestBody Topico topico) {
//        return topicoRepository.save(topico);
//    }


    /*@PostMapping
    public ResponseEntity<DatosListadoTopico> crearTopico(@Valid @RequestBody Topico topico,
                                                          UriComponentsBuilder uriComponentsBuilder) {
            topicoRepository.save(topico);
            DatosListadoTopico datosListadoTopico = new DatosListadoTopico(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechaCreacion(),
                    topico.getStatus(),
                    topico.getAutor().getNombre(),
                    topico.getCurso().getNombre());
            URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(url).body(datosListadoTopico);

    }*/

    @PostMapping
    public ResponseEntity<DatosListadoTopico> crearTopico(@Valid @RequestBody Topico topico,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        // Asegurarse de que el autor y el curso estén completamente cargados
        Usuario autor = usuarioRepository.findById(topico.getAutor().getId()).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        Curso curso = cursoRepository.findById(topico.getCurso().getId()).orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        topico.setAutor(autor);
        topico.setCurso(curso);

        topicoRepository.save(topico);

        DatosListadoTopico datosListadoTopico = new DatosListadoTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosListadoTopico);
    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(size = 30,sort="fechaCreacion") Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    //retorna los datos de un endPoint en especifico
    @GetMapping("/{id}")
    public ResponseEntity<?> retornaDatosTopico(@PathVariable Long id) {
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
           return ResponseEntity.ok( new DatosListadoTopico(
                   topico.getId(),
                   topico.getTitulo(),
                   topico.getMensaje(),
                   topico.getFechaCreacion(),
                   topico.getStatus(),
                   topico.getAutor().getNombre(),
                   topico.getCurso().getNombre()));
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
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Topico no encontrado");
        }
    }


}
