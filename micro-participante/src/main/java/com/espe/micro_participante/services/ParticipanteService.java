package com.espe.micro_participante.services;

import com.espe.micro_participante.model.entity.Participante;
import java.util.List;
import java.util.Optional;

public interface ParticipanteService {
    List<Participante> findAll(); // Obtener todos los participantes
    Optional<Participante> findById(Long id); // Obtener un participante por ID
    Participante save(Participante participante); // Guardar un participante
    void deleteById(Long id); // Eliminar un participante por ID
}
