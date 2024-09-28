package com.example.projeto_integrador.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_integrador.data.AgendamentoRequest;
import com.example.projeto_integrador.data.HorarioDisponivelData;
import com.example.projeto_integrador.service.AgendamentoService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("agendamento")
@CrossOrigin("*")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping()
    public ResponseEntity agendamento(@RequestBody AgendamentoRequest agendamentoRequest){
        var agendamento = service.agendamento(agendamentoRequest);
        return ResponseEntity.ok(agendamento);
    }
    
   @GetMapping("/disponiveis")
    public ResponseEntity<List<String>> verificaHorarioDisponivel(@RequestParam String data, @RequestParam String horario, @RequestParam String medioEmail) {
    HorarioDisponivelData horarioDisponivelData = new HorarioDisponivelData(data, horario, medioEmail);
    var horariosDisponiveis = service.verificaHorarioDisponivel(horarioDisponivelData);
    return ResponseEntity.ok(horariosDisponiveis);
    }

}
