package com.nutricao.macros_game.service;

import com.nutricao.macros_game.model.MacrosInput;
import com.nutricao.macros_game.model.Paciente;
import com.nutricao.macros_game.repository.MacrosInputRepository;
import com.nutricao.macros_game.repository.PacienteRepository;
import com.nutricao.macros_game.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MacrosInputService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MacrosInputRepository macrosInputRepository;

    public MacrosInputService(MacrosInputRepository macrosInputRepository) {
        this.macrosInputRepository = macrosInputRepository;
    }

    public MacrosInput criarMacrosInput(MacrosInput macrosInput, Long pacienteId) {
        try{
            // Calcula as kcal totais antes de salvar o input
            macrosInput.setKcalTotais(calculaKcalTotaisInput(macrosInput));

            // Busca o paciente pelo ID
            Paciente paciente = pacienteRepository.findById(pacienteId)
                    .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com o ID: " + pacienteId));

            // Associa o paciente ao MacrosInput
            macrosInput.setPaciente(paciente);

            // Salva o novo MacrosInput no banco
            return macrosInputRepository.save(macrosInput);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar Input dos macros: " + e.getMessage(), e);
        }

    }



    public Optional<MacrosInput> buscarPorId(Long id) {
        return macrosInputRepository.findById(id);
    }

    public List<MacrosInput> listarPorPaciente(Long pacienteId) {
        return macrosInputRepository.findByPacienteId(pacienteId);
    }

    public List<MacrosInput> listarTodos() {
        return macrosInputRepository.findAll();
    }

    public void deletarInput(Long id) {
        macrosInputRepository.deleteById(id);
    }

    public Double calculaKcalTotaisInput(@org.jetbrains.annotations.NotNull MacrosInput valores){
        if(valores.getFlagKcal().equals(true)){
            // se os valores forem em kcal, soma todos
            return Util.arredondar(valores.getCarboidrato() + valores.getProteina() + valores.getLipidio());
        } else {
            // se os valores forem em gramas, converte para kcal e soma todos
            return Util.arredondar((valores.getCarboidrato() * 4) + (valores.getProteina() * 4) + (valores.getLipidio() * 9));
        }
    }
}
