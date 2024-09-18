package com.example.projeto_integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_integrador.data.PacienteData;
import com.example.projeto_integrador.service.PacienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping()
    public ResponseEntity registrarPaciente(@RequestBody PacienteData pacienteData){
        var paciente = service.registrarPaciente(pacienteData);
        return ResponseEntity.ok(paciente);
    }
    
}
