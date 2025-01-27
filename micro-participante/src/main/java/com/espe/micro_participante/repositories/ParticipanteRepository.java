package com.espe.micro_participante.repositories;

import com.espe.micro_participante.model.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
}
