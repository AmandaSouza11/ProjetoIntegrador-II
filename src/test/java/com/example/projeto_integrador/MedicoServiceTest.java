package com.example.projeto_integrador;

import com.example.projeto_integrador.data.MedicoData;
import com.example.projeto_integrador.data.MedicoLista;
import com.example.projeto_integrador.data.MedicosInfo;
import com.example.projeto_integrador.entity.MedicoEntity;
import com.example.projeto_integrador.repository.AgendamentoRepository;
import com.example.projeto_integrador.repository.MedicoRepository;
import com.example.projeto_integrador.service.MedicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MedicoServiceTest {

    private MedicoRepository medicoRepository;
    private AgendamentoRepository agendamentoRepository;
    private MedicoService medicoService;

    @BeforeEach
    void setup() {
        medicoRepository = mock(MedicoRepository.class);
        agendamentoRepository = mock(AgendamentoRepository.class);
        medicoService = new MedicoService();

        ReflectionTestUtils.setField(medicoService, "repository", medicoRepository);
        ReflectionTestUtils.setField(medicoService, "agendamentoRepository", agendamentoRepository);
    }

    private MedicoData criarMedicoData(String nome, String email, String especialidade) {
        return new MedicoData(
            nome,
            "123.456.789-00",
            "11999999999",
            email,
            "senha123",
            "RA123",
            "CRM12345",
            "CRN67890",
            "01234-567",
            "Centro",
            "Rua A",
            "123",
            "Ap 101",
            "São Paulo",
            especialidade
        );
    }

    @Test
    void testRegistrarMedico_Sucesso() {
        MedicoData medicoData = criarMedicoData("Dr. João", "medico@example.com", "Cardiologia");
        MedicoEntity medicoEntity = new MedicoEntity(medicoData);

        when(medicoRepository.save(any(MedicoEntity.class))).thenReturn(medicoEntity);

        MedicoEntity resultado = medicoService.registrarMedico(medicoData);

        assertNotNull(resultado);
        assertEquals("Dr. João", resultado.getNome());
        assertEquals("Cardiologia", resultado.getEspecialidade());
        verify(medicoRepository, times(1)).save(any(MedicoEntity.class));
    }

    @Test
    void testListarMedicosComEspecialidade_Sucesso() {
        String especialidade = "Cardiologia";
        MedicoEntity medico1 = new MedicoEntity(criarMedicoData("Dr. João", "joao@example.com", especialidade));
        MedicoEntity medico2 = new MedicoEntity(criarMedicoData("Dr. Carlos", "carlos@example.com", especialidade));

        when(medicoRepository.findAllByEspecialidade(especialidade)).thenReturn(List.of(medico2, medico1));

        List<MedicosInfo> resultado = medicoService.listarMedicos(especialidade);

        assertEquals(2, resultado.size());
        assertEquals("Dr. Carlos", resultado.get(0).getNome());
        assertEquals("Dr. João", resultado.get(1).getNome());
    }

    @Test
    void testPerfilMedico_Sucesso() {
        String email = "medico@example.com";
        MedicoEntity medico = new MedicoEntity(criarMedicoData("Dr. João", email, "Cardiologia"));

        when(medicoRepository.findByEmail(email)).thenReturn(Optional.of(medico));

        MedicoEntity resultado = medicoService.perfilMedico(email);

        assertNotNull(resultado);
        assertEquals("Dr. João", resultado.getNome());
    }

    @Test
    void testPerfilMedico_Falha_MedicoNaoEncontrado() {
        String email = "medico_inexistente@example.com";

        when(medicoRepository.findByEmail(email)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            medicoService.perfilMedico(email);
        });

        assertEquals("Medico não encontrado", thrown.getMessage());
    }

    @Test
    void testConsultaDoDia_Sucesso() {
        String email = "medico@example.com";
        int consultasHoje = 5;

        when(agendamentoRepository.countConsultationsTodayByMedicoEmail(email)).thenReturn(consultasHoje);

        int resultado = medicoService.consultaDoDia(email);

        assertEquals(consultasHoje, resultado);
    }

    @Test
    void testListarMedicos_SemEspecialidade() {
        MedicoLista medico1 = mock(MedicoLista.class);
        MedicoLista medico2 = mock(MedicoLista.class);
    
        when(medico1.getNome()).thenReturn("Dr. João");
        when(medico1.getEmail()).thenReturn("joao@example.com");
    
        when(medico2.getNome()).thenReturn("Dr. Carlos");
        when(medico2.getEmail()).thenReturn("carlos@example.com");
    
        when(medicoRepository.listarMedicos()).thenReturn(List.of(medico1, medico2));
    
        List<MedicoLista> resultado = medicoService.listarMedicos();
    
        assertEquals(2, resultado.size());
        assertEquals("Dr. João", resultado.get(0).getNome());
        assertEquals("Dr. Carlos", resultado.get(1).getNome());
    }
    
}
