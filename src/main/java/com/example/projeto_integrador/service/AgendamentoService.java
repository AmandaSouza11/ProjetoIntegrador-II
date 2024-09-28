package com.example.projeto_integrador.service;

import java.util.stream.Collectors;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.projeto_integrador.data.AgendamentoRequest;
import com.example.projeto_integrador.data.HorarioDisponivelData;
import com.example.projeto_integrador.entity.AgendamentoEntity;
import com.example.projeto_integrador.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    private static final List<String> HORARIOS_PADRAO = Arrays.asList(
        "08:00", "09:00",  "10:00", "11:00", 
        "13:00", "14:00", "15:00", "16:00", "17:00"
    );

    public AgendamentoEntity agendamento(AgendamentoRequest agendamentoRequest){
        var agendamento = new AgendamentoEntity(agendamentoRequest,"AGENDADO");
        return repository.save(agendamento);
    }

    public List<String> verificaHorarioDisponivel(HorarioDisponivelData horarioDisponivelData) {
        List<AgendamentoEntity> agendamentos = repository.findByMedicoEmailAndData(horarioDisponivelData.getMedioEmail(), horarioDisponivelData.getData());

        List<String> horariosOcupados = agendamentos.stream()
            .map(AgendamentoEntity::getHorario)
            .collect(Collectors.toList());

        List<String> horariosDisponiveis = HORARIOS_PADRAO.stream()
            .filter(h -> !horariosOcupados.contains(h))
            .collect(Collectors.toList());

        return horariosDisponiveis;
    }
    
}
