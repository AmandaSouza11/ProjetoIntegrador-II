package com.example.projeto_integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_integrador.data.MedicoData;
import com.example.projeto_integrador.service.MedicoService;

@RestController
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping()
    public ResponseEntity registrarMedico(@RequestBody MedicoData medicoData){
        var medico = service.registrarMedico(medicoData);
        return ResponseEntity.ok(medico);
    }
    
}
