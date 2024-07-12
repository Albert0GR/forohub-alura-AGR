package com.agrsystems.forohub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@Getter         //CREA TODOS LOS GETTERS
@NoArgsConstructor  //CREA UN CONSTRUCTOR SIN ARGUMENTOS
@AllArgsConstructor //CONSTRUCTOR CON ARGUMENTOS
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String categoria;
}