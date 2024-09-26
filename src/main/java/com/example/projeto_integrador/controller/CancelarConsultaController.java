package com.example.projeto_integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.projeto_integrador.service.CancelarConsultaService;

@RestController
@RequestMapping("cancelar")
@CrossOrigin("*")
public class CancelarConsultaController {

    @Autowired
    private CancelarConsultaService service;

    @PostMapping("{id}")
    public ResponseEntity cancelarConsulta(@PathVariable Long id){
        service.cancelarConsulta(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    
}
