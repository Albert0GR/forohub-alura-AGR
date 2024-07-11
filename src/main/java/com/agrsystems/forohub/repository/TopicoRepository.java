package com.agrsystems.forohub.repository;

import com.agrsystems.forohub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
