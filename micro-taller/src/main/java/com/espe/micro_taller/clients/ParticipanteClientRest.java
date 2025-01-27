package com.espe.micro_taller.clients;

import com.espe.micro_taller.model.Participantes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micro-participante", url = "localhost:8003/participantes")  // Ajusta la URL según el servicio de participantes
public interface ParticipanteClientRest {

    // Método para obtener un participante por su id
    @GetMapping("/{id}")
    Participantes findById(@PathVariable Long id);
}
