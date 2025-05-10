package com.example.projeto_integrador;

import com.example.projeto_integrador.data.HistoricoMedicoData;
import com.example.projeto_integrador.data.HistoricoPacienteData;
import com.example.projeto_integrador.entity.AgendamentoEntity;
import com.example.projeto_integrador.entity.MedicoEntity;
import com.example.projeto_integrador.entity.PacienteEntity;
import com.example.projeto_integrador.repository.AgendamentoRepository;
import com.example.projeto_integrador.repository.MedicoRepository;
import com.example.projeto_integrador.repository.PacienteRepository;
import com.example.projeto_integrador.service.HistoricoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HistoricoServiceTest {

    private AgendamentoRepository agendamentoRepository;
    private MedicoRepository medicoRepository;
    private PacienteRepository pacienteRepository;
    private HistoricoService historicoService;

    @BeforeEach
    void setup() {
        agendamentoRepository = mock(AgendamentoRepository.class);
        medicoRepository = mock(MedicoRepository.class);
        pacienteRepository = mock(PacienteRepository.class);

        historicoService = new HistoricoService();

        ReflectionTestUtils.setField(historicoService, "agendamentoRepository", agendamentoRepository);
        ReflectionTestUtils.setField(historicoService, "medicoRepository", medicoRepository);
        ReflectionTestUtils.setField(historicoService, "pacienteRepository", pacienteRepository);
    }

    @Test
    void testPacienteHistorico_Sucesso() {
        String email = "paciente@example.com";
        AgendamentoEntity agendamento = new AgendamentoEntity();
        agendamento.setMedico_email("medico@example.com");
        agendamento.setData("2025-05-10");
        agendamento.setHorario("08:00");
        agendamento.setId(1L);
        agendamento.setStatus("CONFIRMADO");

        MedicoEntity medico = new MedicoEntity();
        medico.setNome("Dr. João");
        medico.setCrm("12345");
        medico.setEspecialidade("Cardiologia");
        medico.setCep("12345678");
        medico.setBairro("Centro");
        medico.setRua("Rua A");
        medico.setNumero_residencial("123");
        medico.setComplemento("Ap 101");
        medico.setCidade("Cidade X");

        when(agendamentoRepository.findAllByEmail(email)).thenReturn(List.of(agendamento));
        when(medicoRepository.findByEmail("medico@example.com")).thenReturn(Optional.of(medico));

        List<HistoricoPacienteData> resultado = historicoService.pacienteHistorico(email);

        assertEquals(1, resultado.size());
        assertEquals("Dr. João", resultado.get(0).getNomeMedico());
    }

    @Test
    void testPacienteHistorico_Falha_MedicoNaoEncontrado() {
        String email = "paciente@example.com";
        AgendamentoEntity agendamento = new AgendamentoEntity();
        agendamento.setMedico_email("medico@example.com");

        when(agendamentoRepository.findAllByEmail(email)).thenReturn(List.of(agendamento));
        when(medicoRepository.findByEmail("medico@example.com")).thenReturn(Optional.empty());

        List<HistoricoPacienteData> resultado = historicoService.pacienteHistorico(email);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void testMedicoHistorico_Sucesso() {
        String email = "medico@example.com";
        AgendamentoEntity agendamento = new AgendamentoEntity();
        agendamento.setPaciente_email("paciente@example.com");
        agendamento.setData("2025-05-10");
        agendamento.setHorario("08:00");
        agendamento.setId(1L);
        agendamento.setStatus("CONFIRMADO");

        PacienteEntity paciente = new PacienteEntity();
        paciente.setNome("Maria");
        paciente.setTelefone("99999-9999");

        when(agendamentoRepository.findAllByEmailMedico(email)).thenReturn(List.of(agendamento));
        when(pacienteRepository.findByEmail("paciente@example.com")).thenReturn(Optional.of(paciente));

        List<HistoricoMedicoData> resultado = historicoService.medicoHistorico(email);

        assertEquals(1, resultado.size());
        assertEquals("Maria", resultado.get(0).getNomePaciente());
    }

    @Test
    void testMedicoHistorico_Falha_PacienteNaoEncontrado() {
        String email = "medico@example.com";
        AgendamentoEntity agendamento = new AgendamentoEntity();
        agendamento.setPaciente_email("paciente@example.com");

        when(agendamentoRepository.findAllByEmailMedico(email)).thenReturn(List.of(agendamento));
        when(pacienteRepository.findByEmail("paciente@example.com")).thenReturn(Optional.empty());

        List<HistoricoMedicoData> resultado = historicoService.medicoHistorico(email);

        assertTrue(resultado.isEmpty());
    }
}
