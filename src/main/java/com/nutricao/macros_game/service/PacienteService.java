package com.nutricao.macros_game.service;

import com.nutricao.macros_game.model.Paciente;
import com.nutricao.macros_game.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    //  buscar paciente pelo ID
    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id); // Retorna Optional<Paciente>
    }

    //criar um novo paciente
    public Paciente criarPaciente(Paciente paciente) {
        try {
            paciente.setTMB(calcularTMB(paciente));
            System.out.println(paciente.getTMB());
            paciente.setVET(calcularVET(paciente));
            System.out.println(paciente.getVET());
            return pacienteRepository.save(paciente); // Salva o paciente no banco de dados
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //  atualizar um paciente existente
    public Paciente atualizarPaciente(Long id, Paciente pacienteAtualizado) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado com o ID: " + id);
        }
        pacienteAtualizado.setId(id);
        return pacienteRepository.save(pacienteAtualizado); // Atualiza os dados do paciente no banco
    }

    //  deletar um paciente pelo ID
    public void deletarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado com o ID: " + id);
        }
        pacienteRepository.deleteById(id); // Deleta o paciente do banco de dados
    }

    //  listar todos os pacientes
    public Iterable<Paciente> listarTodosPacientes() {
        return pacienteRepository.findAll(); // Retorna todos os pacientes
    }

    public Double calcularTMB(Paciente paciente) {

        if ("masculino".equalsIgnoreCase(paciente.getGenero())) {
            return 66.47 + (13.75 * paciente.getPeso()) + (5 * paciente.getAltura()) - (6.76 * paciente.getIdade());
        } else {
            return 665.1 + (9.56 * paciente.getPeso()) + (1.85 * paciente.getAltura()) - (4.68 * paciente.getIdade());
        }
    }

    public Double calcularVET(Paciente paciente){
        return paciente.getTMB() * paciente.getAtividadeFisica();
    }
}


