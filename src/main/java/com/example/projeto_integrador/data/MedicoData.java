package com.example.projeto_integrador.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedicoData {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
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
