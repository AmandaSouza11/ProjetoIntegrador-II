package com.example.projeto_integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.projeto_integrador.entity.AgendamentoEntity;

import jakarta.transaction.Transactional;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

    @Query(value = "SELECT * FROM agendamento WHERE paciente_email = :email", nativeQuery = true)
    List<AgendamentoEntity> findAllByEmail(@PathVariable String email);

    @Query(value = "SELECT * FROM agendamento WHERE medico_email = :email", nativeQuery = true)
    List<AgendamentoEntity> findAllByEmailMedico(@PathVariable String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE agendamento SET status = 'CANCELADA' WHERE id = :id", nativeQuery = true)
    void cancelarConsulta(@Param("id") Long id);

    @Query(value = "SELECT * FROM agendamento WHERE medico_email = :medicoEmail AND data = :data", nativeQuery = true)
    List<AgendamentoEntity> findByMedicoEmailAndData(@Param("medicoEmail") String medicoEmail, @Param("data") String data);
    
}
