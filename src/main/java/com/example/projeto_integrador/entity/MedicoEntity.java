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

@Table(name = "medico")
@Entity(name = "MedicoEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String ra;
    private String crm;
    private String crn;
    private String cep;
    private String bairro;
    private String rua;
    private String numero_residencial;
    private String complemento;
    private String cidade;
    private String especialidade;
    
}
