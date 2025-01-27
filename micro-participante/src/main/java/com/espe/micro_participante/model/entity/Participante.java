package com.espe.micro_participante.model.entity;

import jakarta.persistence.*;

@Entity
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;

    // Getters y Setters
}
