package com.nutricao.macros_game.repository;

import com.nutricao.macros_game.model.MacrosInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MacrosInputRepository extends JpaRepository<MacrosInput, Long> {
}
