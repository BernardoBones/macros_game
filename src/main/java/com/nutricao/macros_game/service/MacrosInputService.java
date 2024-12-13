package com.nutricao.macros_game.service;

import com.nutricao.macros_game.model.MacrosInput;
import com.nutricao.macros_game.repository.MacrosInputRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MacrosInputService {

    private final MacrosInputRepository macrosInputRepository;

    public MacrosInputService(MacrosInputRepository macrosInputRepository) {
        this.macrosInputRepository = macrosInputRepository;
    }

    public MacrosInput criarInput(MacrosInput macrosInput) {
        return macrosInputRepository.save(macrosInput);
    }

    public Optional<MacrosInput> buscarPorId(Long id) {
        return macrosInputRepository.findById(id);
    }

    public List<MacrosInput> listarTodos() {
        return macrosInputRepository.findAll();
    }

    public void deletarInput(Long id) {
        macrosInputRepository.deleteById(id);
    }
}
