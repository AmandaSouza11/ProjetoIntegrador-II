package com.example.projeto_integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projeto_integrador.entity.AgendamentoEntity;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {
    
}
