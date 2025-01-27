package com.espe.micro_taller.controllers;

import com.espe.micro_taller.clients.UsuarioClientRest;
import com.espe.micro_taller.model.entity.Taller;
import com.espe.micro_taller.repositories.TallerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/talleres")
public class TallerController {

    @Autowired
    private TallerRepository tallerRepository;

    @Autowired
    private UsuarioClientRest usuarioClientRest;

    // Inscribir un participante en un taller
    @PostMapping("/{tallerId}/inscribir/{participanteId}")
    public Taller inscribirParticipante(@PathVariable Long tallerId, @PathVariable Long participanteId) {
        Optional<Taller> tallerOpt = tallerRepository.findById(tallerId);
        if (tallerOpt.isPresent()) {
            Taller taller = tallerOpt.get();
            taller.getParticipantesIds().add(participanteId);
            return tallerRepository.save(taller);
        }
        return null;
    }

    // Listar participantes de un taller
    @GetMapping("/{tallerId}/participantes")
    public List<Object> listarParticipantes(@PathVariable Long tallerId) {
        Optional<Taller> tallerOpt = tallerRepository.findById(tallerId);
        return tallerOpt.map(taller -> taller.getParticipantesIds()
                        .stream()
                        .map(usuarioClientRest::obtenerParticipante)
                        .toList())
                .orElse(null);
    }

    // Listar talleres de un participante
    @GetMapping("/participante/{participanteId}")
    public List<Taller> listarTalleresPorParticipante(@PathVariable Long participanteId) {
        return tallerRepository.findAll()
                .stream()
                .filter(taller -> taller.getParticipantesIds().contains(participanteId))
                .toList();
    }

    // Cancelar inscripción de un participante
    @DeleteMapping("/{tallerId}/cancelar/{participanteId}")
    public Taller cancelarInscripcion(@PathVariable Long tallerId, @PathVariable Long participanteId) {
        Optional<Taller> tallerOpt = tallerRepository.findById(tallerId);
        if (tallerOpt.isPresent()) {
            Taller taller = tallerOpt.get();
            taller.getParticipantesIds().remove(participanteId);
            return tallerRepository.save(taller);
        }
        return null;
    }
}
