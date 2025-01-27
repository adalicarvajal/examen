package com.espe.micro_taller.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UsuarioClientRest {

    @GetMapping("/participantes/{id}")
    Object obtenerParticipante(@PathVariable Long id);
}

