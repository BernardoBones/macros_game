package com.nutricao.macros_game.repository;

import com.nutricao.macros_game.model.Macros;
import com.nutricao.macros_game.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MacrosRepository extends JpaRepository<Macros, Long> {
    Optional<Macros> findByPacienteId(Long pacienteId);
}
