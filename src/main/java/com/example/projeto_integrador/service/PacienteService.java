package com.example.projeto_integrador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_integrador.data.PacienteData;
import com.example.projeto_integrador.data.PacienteLista;
import com.example.projeto_integrador.entity.PacienteEntity;
import com.example.projeto_integrador.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public PacienteEntity registrarPaciente(PacienteData pacienteData){
        var paciente = new PacienteEntity(pacienteData);
        return repository.save(paciente);
    }
    
    public PacienteEntity perfilPaciente(String email){
        var paciente = repository.findByEmail(email);
        
        if (paciente.isPresent()) {
            return paciente.get(); 
        }
        
        throw new RuntimeException("Paciente não encontrado");  
    }

    public List<PacienteLista> listarPacientes(){
        return repository.listaPacientes();
    }
}
