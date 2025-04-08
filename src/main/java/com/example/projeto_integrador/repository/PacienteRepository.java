package com.example.projeto_integrador.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import com.example.projeto_integrador.data.PacienteLista;
import com.example.projeto_integrador.entity.PacienteEntity;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    Optional<PacienteEntity> findByEmail(String email);

    @Query(value = "SELECT nome, email FROM paciente", nativeQuery = true)
    List<PacienteLista> listaPacientes();
    
}
