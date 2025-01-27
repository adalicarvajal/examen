package com.espe.micro_taller.repositories;

import com.espe.micro_taller.model.entity.Taller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TallerRepository extends JpaRepository<Taller, Long> {
    Optional<Taller> findById(Long id);

    List<Taller> findTalleresById(Long participanteId);
}
