package com.example.projeto_integrador.data;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String senha;
}
