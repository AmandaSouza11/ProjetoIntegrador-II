package com.example.projeto_integrador;

import com.example.projeto_integrador.entity.MedicoEntity;
import com.example.projeto_integrador.entity.PacienteEntity;
import com.example.projeto_integrador.repository.MedicoRepository;
import com.example.projeto_integrador.repository.PacienteRepository;
import com.example.projeto_integrador.service.LoginService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class LoginServiceTest {

    private MedicoRepository medicoRepository;
    private PacienteRepository pacienteRepository;
    private LoginService loginService;

    @BeforeEach
    void setup() {
        medicoRepository = mock(MedicoRepository.class);
        pacienteRepository = mock(PacienteRepository.class);
        
        loginService = new LoginService();
        
        ReflectionTestUtils.setField(loginService, "medicoRepository", medicoRepository);
        ReflectionTestUtils.setField(loginService, "pacienteRepository", pacienteRepository);
    }

    @Test
    void testLoginMedico_Sucesso() {
        String email = "medico@example.com";
        String senha = "senha123";
        
        MedicoEntity medico = new MedicoEntity();
        medico.setEmail(email);
        medico.setSenha(senha);
        medico.setRole("ROLE_MEDICO");

        when(medicoRepository.findByEmail(email)).thenReturn(Optional.of(medico));

        String role = loginService.login(email, senha);

        assertEquals("ROLE_MEDICO", role);
    }

    @Test
    void testLoginPaciente_Sucesso() {
        String email = "paciente@example.com";
        String senha = "senha123";
        
        PacienteEntity paciente = new PacienteEntity();
        paciente.setEmail(email);
        paciente.setSenha(senha);
        paciente.setRole("ROLE_PACIENTE");

        when(pacienteRepository.findByEmail(email)).thenReturn(Optional.of(paciente));

        String role = loginService.login(email, senha);

        assertEquals("ROLE_PACIENTE", role);
    }

    @Test
    void testLogin_Falha_SenhaIncorreta() {
        String email = "medico@example.com";
        String senha = "senhaErrada";
        
        MedicoEntity medico = new MedicoEntity();
        medico.setEmail(email);
        medico.setSenha("senha123");
        medico.setRole("ROLE_MEDICO");

        when(medicoRepository.findByEmail(email)).thenReturn(Optional.of(medico));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            loginService.login(email, senha);
        });

        assertEquals("Email ou senha inválidos", thrown.getMessage());
    }

    @Test
    void testLogin_Falha_EmailNaoEncontrado() {
        String email = "inexistente@example.com";
        String senha = "qualquerSenha";
        
        when(medicoRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(pacienteRepository.findByEmail(email)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            loginService.login(email, senha);
        });

        assertEquals("Email ou senha inválidos", thrown.getMessage());
    }

    @Test
    void testLogin_Falha_SenhaMedicoIncorreta() {
        String email = "medico@example.com";
        String senha = "senhaErrada";

        MedicoEntity medico = new MedicoEntity();
        medico.setEmail(email);
        medico.setSenha("senha123");
        medico.setRole("ROLE_MEDICO");

        when(medicoRepository.findByEmail(email)).thenReturn(Optional.of(medico));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            loginService.login(email, senha);
        });

        assertEquals("Email ou senha inválidos", thrown.getMessage());
    }
}
