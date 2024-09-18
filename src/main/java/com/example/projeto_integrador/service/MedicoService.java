package com.example.projeto_integrador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_integrador.data.MedicoData;
import com.example.projeto_integrador.entity.MedicoEntity;
import com.example.projeto_integrador.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public MedicoEntity registrarMedico(MedicoData medicoData){
        var medico = new MedicoEntity(medicoData);
        return repository.save(medico);
    }
    
}
