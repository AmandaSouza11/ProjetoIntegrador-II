package com.example.projeto_integrador;

import com.example.projeto_integrador.data.PacienteData;
import com.example.projeto_integrador.data.PacienteLista;
import com.example.projeto_integrador.entity.PacienteEntity;
import com.example.projeto_integrador.repository.PacienteRepository;
import com.example.projeto_integrador.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PacienteServiceTest {

    private PacienteRepository pacienteRepository;
    private PacienteService pacienteService;

    @BeforeEach
    void setup() {
        pacienteRepository = mock(PacienteRepository.class);
        pacienteService = new PacienteService();
        ReflectionTestUtils.setField(pacienteService, "repository", pacienteRepository);
    }

    private PacienteData criarPacienteData() {
        PacienteData data = new PacienteData();
        data.setNome("Maria");
        data.setData_nascimento("1990-01-01");
        data.setCpf("123.456.789-00");
        data.setTelefone("11999999999");
        data.setEmail("maria@example.com");
        data.setSenha("senha123");
        data.setCep("01234-567");
        data.setBairro("Centro");
        data.setRua("Rua B");
        data.setNumero_residencial("123");
        data.setComplemento("Ap 202");
        data.setCidade("São Paulo");
        return data;
    }

    @Test
    void testRegistrarPaciente_Sucesso() {
        PacienteData pacienteData = criarPacienteData();
        PacienteEntity pacienteEntity = new PacienteEntity(pacienteData);

        when(pacienteRepository.save(any(PacienteEntity.class))).thenReturn(pacienteEntity);

        PacienteEntity resultado = pacienteService.registrarPaciente(pacienteData);

        assertNotNull(resultado);
        assertEquals("Maria", resultado.getNome());
        verify(pacienteRepository, times(1)).save(any(PacienteEntity.class));
    }

    @Test
    void testPerfilPaciente_Sucesso() {
        String email = "maria@example.com";
        PacienteEntity paciente = new PacienteEntity(criarPacienteData());

        when(pacienteRepository.findByEmail(email)).thenReturn(Optional.of(paciente));

        PacienteEntity resultado = pacienteService.perfilPaciente(email);

        assertNotNull(resultado);
        assertEquals(email, resultado.getEmail());
    }

    @Test
    void testPerfilPaciente_Falha_PacienteNaoEncontrado() {
        String email = "inexistente@example.com";
        when(pacienteRepository.findByEmail(email)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pacienteService.perfilPaciente(email);
        });

        assertEquals("Paciente não encontrado", exception.getMessage());
    }

    @Test
    void testListarPacientes_Sucesso() {
        PacienteLista paciente1 = mock(PacienteLista.class);
        PacienteLista paciente2 = mock(PacienteLista.class);

        when(paciente1.getNome()).thenReturn("Maria");
        when(paciente1.getEmail()).thenReturn("maria@example.com");

        when(paciente2.getNome()).thenReturn("José");
        when(paciente2.getEmail()).thenReturn("jose@example.com");

        when(pacienteRepository.listaPacientes()).thenReturn(List.of(paciente1, paciente2));

        List<PacienteLista> resultado = pacienteService.listarPacientes();

        assertEquals(2, resultado.size());
        assertEquals("Maria", resultado.get(0).getNome());
        assertEquals("José", resultado.get(1).getNome());
    }
}
