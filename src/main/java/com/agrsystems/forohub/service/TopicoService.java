package com.agrsystems.forohub.service;

import com.agrsystems.forohub.model.Topico;
import com.agrsystems.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico saveTopico(Topico topico) throws Exception {
        if (topicoRepository.existsByTituloOrMensaje(topico.getTitulo(), topico.getMensaje())) {
            throw new Exception("El título y mensaje ya existen");
        }
        return topicoRepository.save(topico);
    }
}
