package com.example.projeto_integrador.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;
import com.example.projeto_integrador.data.EnderecoData;

@Service
public class CorreioService {

    public EnderecoData recuperarEndereco(String cep) throws Exception {

        String urlString = "https://viacep.com.br/ws/" + cep + "/json/";
        URL url = new URL(urlString);
    
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000); 
        connection.setReadTimeout(5000); 

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder response = new StringBuilder();
    
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
    
        ObjectMapper objectMapper = new ObjectMapper();
        EnderecoData enderecoData = objectMapper.readValue(response.toString(), EnderecoData.class);
    
        return enderecoData;
    }    
    
}
