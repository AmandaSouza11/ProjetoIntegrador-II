package com.example.projeto_integrador.data;

import lombok.Data;

@Data
public class HistoricoMedicoData {

    private String nomePaciente;
    private String telefone;
    private String data;
    private String horario;
    private Long idAgendamento;
    private String status;
    
}
