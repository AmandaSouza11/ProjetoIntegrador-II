package com.example.projeto_integrador.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.projeto_integrador.data.MedicoData;
import com.example.projeto_integrador.data.MedicoLista;
import com.example.projeto_integrador.data.MedicosInfo;
import com.example.projeto_integrador.entity.MedicoEntity;
import com.example.projeto_integrador.repository.AgendamentoRepository;
import com.example.projeto_integrador.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public MedicoEntity registrarMedico(MedicoData medicoData){
        var medico = new MedicoEntity(medicoData);
        return repository.save(medico);
    }

    public List<MedicosInfo> listarMedicos(@PathVariable String especialidade){
        return repository.findAllByEspecialidade(especialidade).stream()
        .sorted(Comparator.comparing(MedicoEntity::getNome)) 
        .map(MedicosInfo::new)
        .collect(Collectors.toList());
    }

    public MedicoEntity perfilMedico(String email){
        var medico = repository.findByEmail(email);
        
        if (medico.isPresent()) {
            return medico.get(); 
        }
        
        throw new RuntimeException("Medico não encontrado");  
    }

    public int consultaDoDia(String email){
        return agendamentoRepository.countConsultationsTodayByMedicoEmail(email);
    }

    public List<MedicoLista> listarMedicos(){
        return repository.listarMedicos();
    }
    
}
