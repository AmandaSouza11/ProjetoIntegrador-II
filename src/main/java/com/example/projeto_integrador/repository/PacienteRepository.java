package com.example.projeto_integrador.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto_integrador.entity.PacienteEntity;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    
}
