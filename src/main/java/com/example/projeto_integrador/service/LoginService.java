package com.example.projeto_integrador.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_integrador.entity.MedicoEntity;
import com.example.projeto_integrador.entity.PacienteEntity;
import com.example.projeto_integrador.repository.MedicoRepository;
import com.example.projeto_integrador.repository.PacienteRepository;

@Service
public class LoginService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public String login(String email, String senha) {

        Optional<MedicoEntity> medico = medicoRepository.findByEmail(email);
        if (medico.isPresent() && medico.get().getSenha().equals(senha)) {
            return medico.get().getRole();
        }

        Optional<PacienteEntity> paciente = pacienteRepository.findByEmail(email);
        if (paciente.isPresent() && paciente.get().getSenha().equals(senha)) {
            return paciente.get().getRole();
        }

        throw new RuntimeException("Email ou senha inválidos");
    }
    
}
