package com.example.projeto_integrador.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoData {

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("logradouro")
    private String rua;

    @JsonProperty("localidade")
    private String cidade;

    @JsonProperty("bairro")
    private String bairro;
    
}
