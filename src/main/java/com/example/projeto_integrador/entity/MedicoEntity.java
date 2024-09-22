package com.example.projeto_integrador.entity;

import com.example.projeto_integrador.data.MedicoData;

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
    private String role;

    public MedicoEntity(MedicoData data) {
        this.nome = data.getNome();
        this.cpf = data.getCpf();
        this.telefone = data.getTelefone();
        this.email = data.getEmail();
        this.senha = data.getSenha();
        this.ra = data.getRa();
        this.crm = data.getCrm();
        this.crn = data.getCrn();
        this.cep = data.getCep();
        this.bairro = data.getBairro();
        this.rua = data.getRua();
        this.numero_residencial = data.getNumero_residencial();
        this.complemento = data.getComplemento();
        this.cidade = data.getCidade();
        this.especialidade = data.getEspecialidade();
        this.role = "MEDICO";
    }

}
