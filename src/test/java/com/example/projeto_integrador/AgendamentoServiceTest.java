package com.example.projeto_integrador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.projeto_integrador.data.AgendamentoRequest;
import com.example.projeto_integrador.data.HorarioDisponivelData;
import com.example.projeto_integrador.entity.AgendamentoEntity;
import com.example.projeto_integrador.repository.AgendamentoRepository;
import com.example.projeto_integrador.service.AgendamentoService;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository repository;

    @Test
    void agendamento_DeveRetornarAgendamentoSalvo_QuandoDadosForemValidos() {
        AgendamentoRequest request = new AgendamentoRequest("medico@teste.com","paciente@teste.com", "2025-05-10", "08:00");
        AgendamentoEntity esperado = new AgendamentoEntity(request, "AGENDADO");

        when(repository.save(any(AgendamentoEntity.class))).thenReturn(esperado);

        AgendamentoEntity resultado = agendamentoService.agendamento(request);

        assertNotNull(resultado);
        assertEquals("AGENDADO", resultado.getStatus());
        verify(repository).save(any(AgendamentoEntity.class));
    }

    @Test
    void agendamento_DeveLancarExcecao_QuandoSalvarFalhar() {
        AgendamentoRequest request = new AgendamentoRequest("medico@teste.com","paciente@teste.com", "2025-05-10", "08:00");

        when(repository.save(any())).thenThrow(new RuntimeException("Erro ao salvar"));

        assertThrows(RuntimeException.class, () -> agendamentoService.agendamento(request));
    }

    @Test
    void verificaHorarioDisponivel_DeveRetornarHorariosDisponiveis_QuandoExistiremAgendamentos() {
        HorarioDisponivelData data = new HorarioDisponivelData("2025-05-10", "08:00:00", "medico@teste.com");
        List<AgendamentoEntity> agendados = List.of(
            new AgendamentoEntity(new AgendamentoRequest("medico@teste.com","paciente@teste.com", "2025-05-10", "08:00"), "AGENDADO"),
            new AgendamentoEntity(new AgendamentoRequest("medico@teste.com","paciente@teste.com", "2025-05-10", "10:00"), "AGENDADO")
        );

        when(repository.findByMedicoEmailAndData(data.getMedioEmail(), data.getData())).thenReturn(agendados);

        List<String> disponiveis = agendamentoService.verificaHorarioDisponivel(data);

        assertTrue(disponiveis.contains("09:00"));
        assertFalse(disponiveis.contains("08:00"));
        assertFalse(disponiveis.contains("10:00"));
        assertEquals(7, disponiveis.size());
    }

    @Test
    void verificaHorarioDisponivel_DeveRetornarTodosHorarios_QuandoNaoHouverAgendamentos() {
        HorarioDisponivelData data = new HorarioDisponivelData("2025-05-10", "08:00", "medico@teste.com");

        when(repository.findByMedicoEmailAndData(data.getMedioEmail(), data.getData())).thenReturn(Collections.emptyList());

        List<String> disponiveis = agendamentoService.verificaHorarioDisponivel(data);

        assertEquals(9, disponiveis.size());
        assertTrue(disponiveis.contains("08:00"));
    }

    @Test
    void verificaHorarioDisponivel_DeveLancarExcecao_QuandoRepositoryFalhar() {
        HorarioDisponivelData data = new HorarioDisponivelData("2025-05-10", "08:00", "medico@teste.com");

        when(repository.findByMedicoEmailAndData(anyString(), anyString()))
            .thenThrow(new RuntimeException("Erro no banco"));

        assertThrows(RuntimeException.class, () -> agendamentoService.verificaHorarioDisponivel(data));
    }
}

