package com.example.projeto_integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_integrador.data.EnderecoData;
import com.example.projeto_integrador.service.CorreioService;

@RestController
@RequestMapping("cep")
@CrossOrigin("*")
public class CorreioController {

    @Autowired
    private CorreioService service;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoData> recuperarEndereco(@PathVariable String cep) throws Exception {
        var endereco = service.recuperarEndereco(cep);
        return ResponseEntity.ok(endereco);
    }
    
}
