package com.nutricao.macros_game.repository;

import com.nutricao.macros_game.model.MacrosInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MacrosInputRepository extends JpaRepository<MacrosInput, Long> {
    List<MacrosInput> findByPacienteId(Long pacienteId);
}
