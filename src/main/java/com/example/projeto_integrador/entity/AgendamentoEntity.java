package com.example.projeto_integrador.entity;

import com.example.projeto_integrador.data.AgendamentoRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "agendamento")
@Entity(name = "AgendamentoEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medico_email;
    private String paciente_email;
    private String data;
    private String horario;
    private String status;

    public AgendamentoEntity(AgendamentoRequest data, String status) {
        this.medico_email = data.getMedico_email();
        this.paciente_email = data.getPaciente_email();
        this.data = data.getData();
        this.horario = data.getHorario();
        this.status = status;
    }
}
