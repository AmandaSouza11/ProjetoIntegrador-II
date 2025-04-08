package com.example.projeto_integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_integrador.data.PacienteData;
import com.example.projeto_integrador.service.PacienteService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("paciente")
@CrossOrigin("*")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping()
    public ResponseEntity registrarPaciente(@RequestBody PacienteData pacienteData){
        var paciente = service.registrarPaciente(pacienteData);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/perfil/{email}")
    public ResponseEntity perfilPaciente(@PathVariable String email) {
        var paciente = service.perfilPaciente(email);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/listar")
    public ResponseEntity listarPacientes() {
        var paciente = service.listarPacientes();
        return ResponseEntity.ok(paciente);
    }
    
    
}
