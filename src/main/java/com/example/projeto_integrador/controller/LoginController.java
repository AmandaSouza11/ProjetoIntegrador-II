package com.example.projeto_integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_integrador.data.LoginRequest;
import com.example.projeto_integrador.service.LoginService;

@RestController
@RequestMapping("login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String role = service.login(request.getEmail(), request.getSenha());
        return ResponseEntity.ok(role);
    }
    
}
