package com.example.projeto_integrador;

import com.example.projeto_integrador.data.EnderecoData;
import com.example.projeto_integrador.service.CorreioService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CorreioServiceTest {

    private final CorreioService correioService = new CorreioService();

    @Test
    public void testRecuperarEndereco_Sucesso() throws Exception {
        String cep = "01001000";

        EnderecoData endereco = correioService.recuperarEndereco(cep);

        assertNotNull(endereco);
        assertEquals("01001-000", endereco.getCep());
        assertEquals("Praça da Sé", endereco.getRua());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals("Sé", endereco.getBairro());
    }

    @Test
    public void testRecuperarEndereco_Falha_CEPInvalido() throws Exception {
        String cepInvalido = "00000000"; 

        EnderecoData endereco = correioService.recuperarEndereco(cepInvalido);

        assertNotNull(endereco);
        assertNull(endereco.getRua());
        assertNull(endereco.getCidade());
        assertNull(endereco.getBairro());
    }

}
