package com.espe.micro_taller.model.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    // Relación con TallerParticipante
    @OneToMany(mappedBy = "taller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TallerParticipante> tallerParticipantes;

    public Taller() {
        this.tallerParticipantes = new ArrayList<>();
    }

    // Métodos para agregar y eliminar participantes del taller
    public void addTallerParticipante(TallerParticipante tallerParticipante) {
        this.tallerParticipantes.add(tallerParticipante);
        tallerParticipante.setTaller(this);  // Asignar taller al participante
    }

    public void removeTallerParticipante(TallerParticipante tallerParticipante) {
        this.tallerParticipantes.remove(tallerParticipante);
        tallerParticipante.setTaller(null);  // Eliminar la referencia al taller
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<TallerParticipante> getTallerParticipantes() {
        return tallerParticipantes;
    }

    public void setTallerParticipantes(List<TallerParticipante> tallerParticipantes) {
        this.tallerParticipantes = tallerParticipantes;
    }
}
