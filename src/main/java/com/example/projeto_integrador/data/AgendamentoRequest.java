package com.example.projeto_integrador.data;

import lombok.Data;

@Data
public class AgendamentoRequest {

    private String medico_email;
    private String paciente_email;
    private String data;
    private String horario;
}
