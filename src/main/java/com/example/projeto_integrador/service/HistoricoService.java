package com.example.projeto_integrador.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_integrador.data.HistoricoMedicoData;
import com.example.projeto_integrador.data.HistoricoPacienteData;
import com.example.projeto_integrador.entity.AgendamentoEntity;
import com.example.projeto_integrador.entity.MedicoEntity;
import com.example.projeto_integrador.entity.PacienteEntity;
import com.example.projeto_integrador.repository.AgendamentoRepository;
import com.example.projeto_integrador.repository.MedicoRepository;
import com.example.projeto_integrador.repository.PacienteRepository;

@Service
public class HistoricoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<HistoricoPacienteData> pacienteHistorico(String email) {
            var listAgendamento = agendamentoRepository.findAllByEmail(email);
            
            List<HistoricoPacienteData> historicoPaciente = new ArrayList<>();

            for (AgendamentoEntity agendamento : listAgendamento) {
                Optional<MedicoEntity> infoMedico = medicoRepository.findByEmail(agendamento.getMedico_email());
                
                infoMedico.ifPresent(medico -> {
                    HistoricoPacienteData data = new HistoricoPacienteData();
                    data.setNomeMedico(medico.getNome());
                    data.setCrm(medico.getCrm());
                    data.setEspecialidade(medico.getEspecialidade());
                    data.setCep(medico.getCep());
                    data.setBairro(medico.getBairro());
                    data.setRua(medico.getRua());
                    data.setNumeroResidencial(medico.getNumero_residencial());
                    data.setComplemento(medico.getComplemento());
                    data.setCidade(medico.getCidade());
                    data.setData(agendamento.getData().toString());
                    data.setHorario(agendamento.getHorario().toString());
                    data.setIdAgendamento(agendamento.getId());
                    data.setStatus(agendamento.getStatus());

                    historicoPaciente.add(data);
                });
            }
            
            return historicoPaciente;
        }

        public List<HistoricoMedicoData> medicoHistorico(String email) {
            var listAgendamento = agendamentoRepository.findAllByEmailMedico(email);
            
            List<HistoricoMedicoData> historicoMedico = new ArrayList<>();

            for (AgendamentoEntity agendamento : listAgendamento) {
                Optional<PacienteEntity> infoPaciente = pacienteRepository.findByEmail(agendamento.getPaciente_email());
                
                infoPaciente.ifPresent(paciente -> {
                    HistoricoMedicoData data = new HistoricoMedicoData();
                    data.setNomePaciente(paciente.getNome());
                    data.setTelefone(paciente.getTelefone());
                    data.setData(agendamento.getData().toString());
                    data.setHorario(agendamento.getHorario().toString());
                    data.setIdAgendamento(agendamento.getId());
                    data.setStatus(agendamento.getStatus());

                    historicoMedico.add(data);
                });
            }
            
            return historicoMedico;
        }
    
}
