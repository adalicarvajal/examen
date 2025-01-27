package com.espe.micro_taller.services;

import com.espe.micro_taller.model.Participantes;
import com.espe.micro_taller.model.entity.Taller;
import com.espe.micro_taller.model.entity.TallerParticipante;

import java.util.List;
import java.util.Optional;

public interface TallerServices {
    // Obtener todos los talleres
    List<Taller> findAll();

    // Obtener taller por ID
    Optional<Taller> findById(Long id);

    // Guardar un taller
    Taller save(Taller taller);

    // Eliminar un taller por ID
    void deleteById(Long id);

    // Asignar un participante a un taller
    void asignarParticipante(Long tallerId, Long participanteId);

    // Obtener participantes de un taller
    List<Long> obtenerParticipantes(Long tallerId);



    Optional<TallerParticipante> asignarParticipante(Participantes participante, Long id);

    List<Participantes> listarParticipantesPorTaller(Long tallerId);

    // Listar talleres en los que un participante ha estado inscrito
    List<Taller> listarTalleresPorParticipante(Long participanteId);

    // Eliminar un participante de un taller
    void eliminarParticipante(Long tallerId, Long participanteId);

    void cancelarParticipacion(Long tallerId, Long participanteId);
}
