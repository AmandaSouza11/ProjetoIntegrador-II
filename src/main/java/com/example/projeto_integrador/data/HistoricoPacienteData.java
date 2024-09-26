package com.example.projeto_integrador.data;

import lombok.Data;

@Data
public class HistoricoPacienteData {
    
    private String nomeMedico;
    private String crm;
    private String especialidade;
    private String cep;
    private String bairro; 
    private String rua;
    private String numeroResidencial;
    private String complemento;
    private String cidade; 
    private String data;
    private String horario;
    private Long idAgendamento;
    private String status;
}
