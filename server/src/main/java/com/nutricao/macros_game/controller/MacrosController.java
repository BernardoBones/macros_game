package com.nutricao.macros_game.controller;

import java.util.Optional;
import com.nutricao.macros_game.model.Macros;
import com.nutricao.macros_game.service.MacrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/macros")
public class MacrosController {

    @Autowired
    private MacrosService macrosService;

    /**
     * Busca os macros de um paciente pelo ID.
     * @param pacienteId O ID do paciente.
     * @return Resposta com os macros ou uma mensagem de erro.
     */
    @GetMapping("/{pacienteId}")
    public ResponseEntity<Macros> buscarMacrosPorPacienteId(@PathVariable Long pacienteId) {
        System.out.println(pacienteId);
        Optional<Macros> macros = macrosService.buscarMacrosPorPacienteId(pacienteId);

        return macros.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
