package com.example.projeto_integrador.data;

import com.example.projeto_integrador.entity.MedicoEntity;

import lombok.Data;

@Data
public class MedicosInfo {

    private String nome;
    private String telefone;
    private String email;
    private String cep;
    private String bairro;
    private String rua;
    private String numero_residencial;
    private String complemento;
    private String cidade;
    private String especialidade;

    public MedicosInfo(MedicoEntity entity) {
        this.nome = entity.getNome();
        this.telefone = entity.getTelefone();
        this.email = entity.getEmail();
        this.cep = entity.getCep();
        this.bairro = entity.getBairro();
        this.rua = entity.getRua();
        this.numero_residencial = entity.getNumero_residencial();
        this.complemento = entity.getComplemento();
        this.cidade = entity.getCidade();
        this.especialidade = entity.getEspecialidade();
    }
    
}
