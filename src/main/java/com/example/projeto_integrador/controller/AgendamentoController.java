package com.example.projeto_integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_integrador.data.AgendamentoRequest;
import com.example.projeto_integrador.service.AgendamentoService;


@RestController
@RequestMapping("agendamento")
@CrossOrigin("*")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping()
    public ResponseEntity agendamento(@RequestBody AgendamentoRequest agendamentoRequest){
        var agendamento = service.agendamento(agendamentoRequest);
        return ResponseEntity.ok(agendamento);
    }
    
}
