package com.example.projeto_integrador.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "paciente")
@Entity(name = "PacienteEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String data_nascimento;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private String cep;
    private String bairro;
    private String rua;
    private String numero_residencial;
    private String complemento;
    private String cidade;
    
}
