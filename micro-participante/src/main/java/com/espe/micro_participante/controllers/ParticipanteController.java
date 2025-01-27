package com.espe.micro_participante.controllers;

import com.espe.micro_participante.model.entity.Participante;
import com.espe.micro_participante.repositories.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @GetMapping("/{id}")
    public Optional<Participante> obtenerParticipante(@PathVariable Long id) {
        return participanteRepository.findById(id);
    }

    @PostMapping
    public Participante crearParticipante(@RequestBody Participante participante) {
        return participanteRepository.save(participante);
    }

    @GetMapping
    public List<Participante> listarParticipantes() {
        return participanteRepository.findAll();
    }
}
