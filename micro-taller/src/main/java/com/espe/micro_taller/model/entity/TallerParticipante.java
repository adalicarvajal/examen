package com.espe.micro_taller.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "taller_participantes")
public class TallerParticipante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "participante_id", unique = false)
    private Long participanteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taller_id")
    private Taller taller; // Relación con Taller

    // Getter y Setter para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para participanteId
    public Long getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(Long participanteId) {
        this.participanteId = participanteId;
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    // Modificación del método equals para comparar correctamente los objetos
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TallerParticipante)) {
            return false;
        }
        TallerParticipante o = (TallerParticipante) obj;
        return this.participanteId != null && this.participanteId.equals(o.participanteId)
                && this.taller != null && this.taller.equals(o.taller);
    }

    // Método hashCode para mejorar las comparaciones y optimizar el uso en colecciones
    @Override
    public int hashCode() {
        return Objects.hash(participanteId, taller);
    }
}
