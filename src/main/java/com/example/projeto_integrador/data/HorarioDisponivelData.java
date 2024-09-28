package com.example.projeto_integrador.data;

import lombok.Data;

@Data
public class HorarioDisponivelData {
    
    private String data;
    private String horario;
    private String medioEmail;

    public HorarioDisponivelData(String data, String horario, String medioEmail) {
        this.data = data;
        this.horario = horario;
        this.medioEmail = medioEmail;
    }

}
