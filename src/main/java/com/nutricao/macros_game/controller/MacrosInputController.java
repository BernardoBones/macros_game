package com.nutricao.macros_game.controller;

import com.nutricao.macros_game.model.MacrosInput;
import com.nutricao.macros_game.service.MacrosInputService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/macros-input")
public class MacrosInputController {

    private final MacrosInputService macrosInputService;

    public MacrosInputController(MacrosInputService macrosInputService) {
        this.macrosInputService = macrosInputService;
    }

    @PostMapping("/{pacienteId}")
    public ResponseEntity<MacrosInput> criarMacrosInput(@RequestBody MacrosInput macrosInput, @PathVariable Long pacienteId) {
        MacrosInput novoMacrosInput = macrosInputService.criarMacrosInput(macrosInput, pacienteId);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMacrosInput);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MacrosInput> buscarPorMacrosInputId(@PathVariable Long id) {
        return macrosInputService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<MacrosInput>> listarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(macrosInputService.listarPorPaciente(pacienteId));
    }

    @GetMapping
    public ResponseEntity<List<MacrosInput>> listarTodos() {
        return ResponseEntity.ok(macrosInputService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarInput(@PathVariable Long id) {
        macrosInputService.deletarInput(id);
        return ResponseEntity.noContent().build();
    }
}
