package com.espe.micro_taller.clients;

import com.espe.micro_taller.model.Participantes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "micro-participantes", url = "localhost:8003/api/participantes")  // Ajusta la URL según el servicio de equipos

public interface UsuarioClientRest {

    @GetMapping("/participantes/{id}")
    Object obtenerParticipante(@PathVariable Long id);

    Participantes findById(Long participanteId);
}

