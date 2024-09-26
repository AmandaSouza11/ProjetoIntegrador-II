package com.example.projeto_integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.projeto_integrador.entity.AgendamentoEntity;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

    @Query(value = "SELECT * FROM agendamento WHERE paciente_email = :email", nativeQuery = true)
    List<AgendamentoEntity> findAllByEmail(@PathVariable String email);
    
}
