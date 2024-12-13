package com.nutricao.macros_game.service;

import com.nutricao.macros_game.model.Macros;
import com.nutricao.macros_game.model.Paciente;
import com.nutricao.macros_game.repository.MacrosRepository;
import com.nutricao.macros_game.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MacrosService {

    private final MacrosRepository macrosRepository;

    @Autowired
    public MacrosService(MacrosRepository macrosRepository) {
        this.macrosRepository = macrosRepository;
    }

    public void criarOuAtualizarMacros(Paciente paciente) {
        Optional<Macros> macrosExistente = macrosRepository.findByPacienteId(paciente.getId());

        Macros macros;

        if (macrosExistente.isPresent()) {
            // Atualizar o objeto existente
            macros = macrosExistente.get();
        } else {
            // Criar novo objeto
            macros = new Macros();
            macros.setPaciente(paciente);
        }

        double vet = paciente.getVET();

        // calcula as calorias mínimas e máximas de cada macro
        macros.setProteinaMinKcal(Util.arredondar(vet * 0.10));
        macros.setProteinaMaxKcal(Util.arredondar(vet * 0.35));
        macros.setCarboidratoMinKcal(Util.arredondar(vet * 0.55));
        macros.setCarboidratoMaxKcal(Util.arredondar(vet * 0.75));
        macros.setLipidioMinKcal(Util.arredondar(vet * 0.15));
        macros.setLipidioMaxKcal(Util.arredondar(vet * 0.30));

        // calcula as gramas, baseado nas kcal minímas e máximas de cada macro
        macros.setProteinaMinGramas(Util.arredondar(macros.getProteinaMinKcal() / 4));
        macros.setProteinaMaxGramas(Util.arredondar(macros.getProteinaMaxKcal() / 4));
        macros.setCarboidratoMinGramas(Util.arredondar(macros.getCarboidratoMinKcal() / 4));
        macros.setCarboidratoMaxGramas(Util.arredondar(macros.getCarboidratoMaxKcal() / 4));
        macros.setLipidioMinGramas(Util.arredondar(macros.getLipidioMinKcal() / 9));
        macros.setLipidioMaxGramas(Util.arredondar(macros.getLipidioMaxKcal() / 9));
        macrosRepository.save(macros);
    }

    /**
     * Busca os macros de um paciente pelo ID do paciente.
     * @param pacienteId O ID do paciente.
     * @return Os macros do paciente ou uma mensagem de erro.
     */
    public Optional<Macros> buscarMacrosPorPacienteId(Long pacienteId) {
        return macrosRepository.findByPacienteId(pacienteId);
    }
}
