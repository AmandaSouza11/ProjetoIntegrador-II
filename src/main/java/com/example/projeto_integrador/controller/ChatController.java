package com.example.projeto_integrador.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.projeto_integrador.entity.Message;
import com.example.projeto_integrador.repository.MessageRepository;

@RestController
@RequestMapping("/api/chat")
@SessionAttributes("emailPaciente")
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/mensagens")
    public List<Message> getMensagens(@RequestParam String emailMedico, @RequestParam String emailPaciente) {
        return messageRepository.findByEmailMedicoAndEmailPacienteOrderByTimestamp(emailMedico, emailPaciente);
    }

    @PostMapping("/enviar")
    public Message enviarMensagem(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }
}
