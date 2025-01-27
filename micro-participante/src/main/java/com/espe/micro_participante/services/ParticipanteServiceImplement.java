package com.espe.micro_participante.services;

import com.espe.micro_participante.model.entity.Participante;
import com.espe.micro_participante.model.entity.Participante;
import com.espe.micro_participante.repositories.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteServiceImplement implements ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Override
    public List<Participante> findAll() {
        return participanteRepository.findAll();
    }

    @Override
    public Optional<Participante> findById(Long id) {
        return participanteRepository.findById(id);
    }

    @Override
    public Participante save(Participante participante) {
        return participanteRepository.save(participante);
    }

    @Override
    public void deleteById(Long id) {
        participanteRepository.deleteById(id);
    }
}
