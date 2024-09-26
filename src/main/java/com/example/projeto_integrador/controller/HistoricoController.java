package com.example.projeto_integrador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_integrador.data.HistoricoPacienteData;
import com.example.projeto_integrador.service.HistoricoService;

@RestController
@RequestMapping("historico")
@CrossOrigin("*")
public class HistoricoController {

    @Autowired
    private HistoricoService service;

    @GetMapping("paciente/{email}")
    public ResponseEntity<List<HistoricoPacienteData>> listarMedicos(@PathVariable String email){
        var historico = service.pacienteHistorico(email);
        return ResponseEntity.ok(historico);
    }
    
}
