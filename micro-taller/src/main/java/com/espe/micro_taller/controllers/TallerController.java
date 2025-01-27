package com.espe.micro_taller.controllers;

import com.espe.micro_taller.model.Participantes;

import com.espe.micro_taller.model.entity.Taller;
import com.espe.micro_taller.model.entity.TallerParticipante;
import com.espe.micro_taller.services.TallerServices;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/talleres")
public class TallerController {

    @Autowired
    private TallerServices tallerServices;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Taller taller, BindingResult result) {
        if (validar(result) != null) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tallerServices.save(taller));
    }

    @GetMapping
    public List<Taller> listar() {
        return tallerServices.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@Valid @PathVariable Long id) {
        Optional<Taller> tallerOptional = tallerServices.findById(id);
        if (tallerOptional.isPresent()) {
            return ResponseEntity.ok().body(tallerOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Taller taller, @PathVariable Long id) {
        Optional<Taller> tallerOptional = tallerServices.findById(id);
        if (tallerOptional.isPresent()) {
            Taller tallerDB = tallerOptional.get();
            tallerDB.setNombre(taller.getNombre());
            tallerDB.setDescripcion(taller.getDescripcion());
            return ResponseEntity.status(HttpStatus.CREATED).body(tallerServices.save(tallerDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Taller> tallerOptional = tallerServices.findById(id);
        if (tallerOptional.isPresent()) {
            tallerServices.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> validar(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(
                    err -> errores.put(
                            err.getField(), err.getDefaultMessage()
                    )
            );
            return ResponseEntity.badRequest().body(errores);
        }
        return null;
    }

    @PutMapping("/asignar-participante/{tallerId}")
    public ResponseEntity<?> asignarParticipante(@RequestBody Participantes participante, @PathVariable Long tallerId) {
        Optional<TallerParticipante> o;
        try {
            o = tallerServices.asignarParticipante(participante, tallerId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No existe el taller con el ID " + tallerId + " o error de comunicación: " + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/participantes/{tallerId}")
    public ResponseEntity<List<Participantes>> listarParticipantesPorTaller(@PathVariable Long tallerId) {
        List<Participantes> participantes = tallerServices.listarParticipantesPorTaller(tallerId);
        return ResponseEntity.ok(participantes);
    }

    @DeleteMapping("/cancelar-participacion/{tallerId}/{participanteId}")
    public ResponseEntity<?> cancelarParticipacion(@PathVariable Long tallerId, @PathVariable Long participanteId) {
        tallerServices.cancelarParticipacion(tallerId, participanteId);
        return ResponseEntity.noContent().build();
    }
}
