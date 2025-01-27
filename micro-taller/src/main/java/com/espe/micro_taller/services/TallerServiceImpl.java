package com.espe.micro_taller.services;

import com.espe.micro_taller.clients.ParticipanteClientRest;
import com.espe.micro_taller.model.Participantes;
import com.espe.micro_taller.model.entity.Taller;
import com.espe.micro_taller.model.entity.TallerParticipante;
import com.espe.micro_taller.repositories.TallerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TallerServiceImpl implements TallerServices {

    @Autowired
    private TallerRepository tallerRepository;

    @Autowired
    private ParticipanteClientRest participanteClientRest;

    @Override
    public List<Taller> findAll() {
        return (List<Taller>) tallerRepository.findAll();
    }

    @Override
    public Optional<Taller> findById(Long id) {
        return tallerRepository.findById(id);
    }

    @Override
    public Taller save(Taller taller) {
        return tallerRepository.save(taller);
    }

    @Override
    public void deleteById(Long id) {
        tallerRepository.deleteById(id);
    }

    @Override
    public void asignarParticipante(Long tallerId, Long participanteId) {

    }

    @Override
    public List<Long> obtenerParticipantes(Long tallerId) {
        return List.of();
    }




    @Override
    public Optional<TallerParticipante> asignarParticipante(Participantes participante, Long id) {
        // Buscar el taller por su ID
        Optional<Taller> optional = tallerRepository.findById(id);
        System.out.println(optional.get().getNombre());

        // Verifica si el taller existe
        if (optional.isPresent()) {
            // Busca el participante por su ID usando el cliente REST
            System.out.println(optional.get().getNombre()+"kskks");
            Participantes participanteTemp = participanteClientRest.findById(participante.getId());
            System.out.println(participanteTemp.getApellido());
            if (participanteTemp != null) {
                // Obtiene el taller desde el Optional
                Taller taller = optional.get();

                // Crea una nueva relación entre el taller y el participante
                TallerParticipante tallerParticipanteRel = new TallerParticipante();
                tallerParticipanteRel.setParticipanteId(participanteTemp.getId());

                // Agrega la relación al taller (asumiendo que en la entidad Taller ya tienes una colección de TallerParticipante)
                taller.addTallerParticipante(tallerParticipanteRel);

                // Guarda los cambios en el taller
                tallerRepository.save(taller);

                // Retorna la relación TallerParticipante creada
                return Optional.of(tallerParticipanteRel);
            }
        }

        // Si el taller o el participante no se encuentran, retorna un Optional vacío
        return Optional.empty();
    }

    @Override
    public List<Participantes> listarParticipantesPorTaller(Long tallerId) {
        // Buscar el taller por su ID
        Optional<Taller> optionalTaller = tallerRepository.findById(tallerId);

        // Verificar si el taller existe
        if (optionalTaller.isPresent()) {
            Taller taller = optionalTaller.get();

            // Retornar la lista de participantes a partir de las relaciones TallerParticipante
            return taller.getTallerParticipantes().stream()
                    .map(tallerParticipante -> participanteClientRest.findById(tallerParticipante.getParticipanteId()))
                    .collect(Collectors.toList());
        }

        // Si el taller no se encuentra, retornar una lista vacía
        return List.of();
    }

    @Override
    public List<Taller> listarTalleresPorParticipante(Long participanteId) {
        // Aquí se puede hacer una consulta para listar los talleres en los que un participante ha participado
        return tallerRepository.findTalleresById(participanteId);
    }

    @Override
    public void eliminarParticipante(Long tallerId, Long participanteId) {

    }

    @Override
    public void cancelarParticipacion(Long tallerId, Long participanteId) {
        // Buscar el taller por su ID
        Optional<Taller> optional = tallerRepository.findById(tallerId);
        if (optional.isPresent()) {
            Taller taller = optional.get();
            // Buscar la relación entre el taller y el participante
            Optional<TallerParticipante> tallerParticipanteOptional = taller.getTallerParticipantes().stream()
                    .filter(tallerParticipante -> tallerParticipante.getParticipanteId().equals(participanteId))
                    .findFirst();
            if (tallerParticipanteOptional.isPresent()) {
                // Eliminar la relación entre el taller y el participante
                taller.removeTallerParticipante(tallerParticipanteOptional.get()); // Implementa el método removeTallerParticipante en la clase Taller
                tallerRepository.save(taller); // Guardar los cambios en el taller
            }
        }
    }
}
