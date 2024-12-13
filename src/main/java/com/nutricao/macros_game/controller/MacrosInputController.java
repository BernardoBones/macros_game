package com.nutricao.macros_game.controller;

import com.nutricao.macros_game.model.MacrosInput;
import com.nutricao.macros_game.service.MacrosInputService;
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

    @PostMapping
    public ResponseEntity<MacrosInput> criarInput(@RequestBody MacrosInput macrosInput) {
        MacrosInput novoInput = macrosInputService.criarInput(macrosInput);
        return ResponseEntity.ok(novoInput);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MacrosInput> buscarPorId(@PathVariable Long id) {
        return macrosInputService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
