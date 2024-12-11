package com.nutricao.macros_game.service;

import com.nutricao.macros_game.model.Paciente;
import com.nutricao.macros_game.model.Macros;
import com.nutricao.macros_game.util.Util;
import com.nutricao.macros_game.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final MacrosService macrosService;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, MacrosService macrosService) {
        this.pacienteRepository = pacienteRepository;
        this.macrosService = macrosService;
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente criarPaciente(Paciente paciente) {
        try {
            // calcula a taxa metabólica base e o valor energético total com os dados informados
            paciente.setTMB(calcularTMB(paciente));
            paciente.setVET(calcularVET(paciente));

            // salva os dados do paciente, incluindo TMB e VET
            Paciente pacienteSalvo = pacienteRepository.save(paciente);

            // cria objeto macros associado a esse paciente
            macrosService.criarOuAtualizarMacros(pacienteSalvo);

            return pacienteSalvo;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar paciente: " + e.getMessage(), e);
        }
    }

    public Paciente atualizarPaciente(Long id, Paciente pacienteAtualizado) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findById(id);

        if (pacienteExistente.isEmpty()) {
            throw new RuntimeException("Paciente não encontrado");
        }

        Paciente paciente = pacienteExistente.get();

        paciente.setNome(pacienteAtualizado.getNome());
        paciente.setAltura(pacienteAtualizado.getAltura());
        paciente.setPeso(pacienteAtualizado.getPeso());
        paciente.setGenero(pacienteAtualizado.getGenero());
        paciente.setIdade(pacienteAtualizado.getIdade());
        paciente.setAtividadeFisica(pacienteAtualizado.getAtividadeFisica());

        paciente.setTMB(calcularTMB(paciente));
        paciente.setVET(calcularVET(paciente));

        Paciente pacienteSalvo = pacienteRepository.save(paciente);

        macrosService.criarOuAtualizarMacros(pacienteSalvo);

        return pacienteSalvo;
    }

    public void deletarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado com o ID: " + id);
        }
        pacienteRepository.deleteById(id); // Deleta o paciente do banco de dados
    }

    public Iterable<Paciente> listarTodosPacientes() {
        return pacienteRepository.findAll(); // Retorna todos os pacientes
    }

    public Double calcularTMB(Paciente paciente) {

        if ("masculino".equalsIgnoreCase(paciente.getGenero())) {
            return Util.arredondar(66.47 + (13.75 * paciente.getPeso()) + (5 * paciente.getAltura()) - (6.76 * paciente.getIdade()));
        } else {
            return Util.arredondar(665.1 + (9.56 * paciente.getPeso()) + (1.85 * paciente.getAltura()) - (4.68 * paciente.getIdade()));
        }
    }

    public Double calcularVET(Paciente paciente){
        return Util.arredondar(paciente.getTMB() * paciente.getAtividadeFisica());
    }
}


