package com.example.projeto_integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto_integrador.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByEmailMedicoAndEmailPacienteOrderByTimestamp(String emailMedico, String emailPaciente);
}
