package com.example.projeto_integrador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_integrador.data.MedicoData;
import com.example.projeto_integrador.data.MedicosInfo;
import com.example.projeto_integrador.service.MedicoService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("medico")
@CrossOrigin("*")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping()
    public ResponseEntity registrarMedico(@RequestBody MedicoData medicoData){
        var medico = service.registrarMedico(medicoData);
        return ResponseEntity.ok(medico);
    }

    @GetMapping("/{especialidade}")
    public ResponseEntity<List<MedicosInfo>> listarMedicos(@PathVariable String especialidade){
        var medicos = service.listarMedicos(especialidade);
        return ResponseEntity.ok(medicos);
    }
    
    @GetMapping("/perfil/{email}")
    public ResponseEntity perfilMedico(@PathVariable String email) {
        var medico = service.perfilMedico(email);
        return ResponseEntity.ok(medico);
    }

    @GetMapping("consulta/{email}")
    public ResponseEntity consultaDoDia(@PathVariable String email) {
        var medico = service.consultaDoDia(email);
        return ResponseEntity.ok(medico);
    }
}
