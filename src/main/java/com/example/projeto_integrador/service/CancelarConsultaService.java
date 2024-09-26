package com.example.projeto_integrador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_integrador.repository.AgendamentoRepository;

import jakarta.transaction.Transactional;

@Service
public class CancelarConsultaService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Transactional
    public void cancelarConsulta(Long id){
        agendamentoRepository.cancelarConsulta(id);
    }
    
}
