package com.example.projeto_integrador.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.projeto_integrador.entity.PacienteEntity;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    Optional<PacienteEntity> findByEmail(String email);
    
}
