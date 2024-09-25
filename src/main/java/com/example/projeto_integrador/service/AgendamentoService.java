package com.example.projeto_integrador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_integrador.data.AgendamentoRequest;
import com.example.projeto_integrador.entity.AgendamentoEntity;
import com.example.projeto_integrador.repository.AgendamentoRepository;


@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    public AgendamentoEntity agendamento(AgendamentoRequest agendamentoRequest){
        var agendamento = new AgendamentoEntity(agendamentoRequest,"AGENDADO");
        return repository.save(agendamento);
    }
    
}
