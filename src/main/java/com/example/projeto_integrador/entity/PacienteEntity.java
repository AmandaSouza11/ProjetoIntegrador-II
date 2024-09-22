package com.example.projeto_integrador.entity;

import com.example.projeto_integrador.data.PacienteData;

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
    private String role;

    public PacienteEntity(PacienteData data) {
        this.nome = data.getNome();
        this.data_nascimento = data.getData_nascimento();
        this.cpf = data.getCpf();
        this.telefone = data.getTelefone();
        this.email = data.getEmail();
        this.senha = data.getSenha();
        this.cep = data.getCep();
        this.bairro = data.getBairro();
        this.rua = data.getRua();
        this.numero_residencial = data.getNumero_residencial();
        this.complemento = data.getComplemento();
        this.cidade = data.getCidade(); 
        this.role = "PACIENTE";
    }
    
}
