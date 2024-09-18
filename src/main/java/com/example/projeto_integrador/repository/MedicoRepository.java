package com.example.projeto_integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.projeto_integrador.entity.MedicoEntity;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

    @Query(value = "SELECT * FROM medico WHERE especialidade = :especialidade", nativeQuery = true)
    List<MedicoEntity> findAllByEspecialidade(@PathVariable String especialidade);
    
}
