package com.example.projeto_integrador;

import com.example.projeto_integrador.repository.AgendamentoRepository;
import com.example.projeto_integrador.service.CancelarConsultaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

public class CancelarConsultaServiceTest {

    private AgendamentoRepository agendamentoRepository;
    private CancelarConsultaService cancelarConsultaService;

    @BeforeEach
    void setup() {
        agendamentoRepository = mock(AgendamentoRepository.class);
        cancelarConsultaService = new CancelarConsultaService();
        ReflectionTestUtils.setField(cancelarConsultaService, "agendamentoRepository", agendamentoRepository);
    }

    @Test
    void testCancelarConsulta_DeveChamarMetodoDoRepositorio() {
        Long idConsulta = 1L;

        cancelarConsultaService.cancelarConsulta(idConsulta);

        verify(agendamentoRepository, times(1)).cancelarConsulta(idConsulta);
    }
}
