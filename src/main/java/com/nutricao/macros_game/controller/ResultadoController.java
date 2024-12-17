package com.nutricao.macros_game.controller;

import com.nutricao.macros_game.model.Macros;
import com.nutricao.macros_game.model.MacrosInput;
import com.nutricao.macros_game.model.Resultado;
import com.nutricao.macros_game.repository.MacrosRepository;
import com.nutricao.macros_game.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/resultados")
public class ResultadoController {

    @Autowired
    private MacrosRepository macrosRepository;

    @Autowired
    private ResultadoService resultadoService;

    // Endpoint para calcular o resultado com base no input do usuário
    @PostMapping("/calcular/{pacienteId}")
    public ResponseEntity<String> calcularResultado(@PathVariable Long pacienteId, @RequestBody MacrosInput inputUsuario) {
        // Buscar as macros esperadas no banco com base no pacienteId
        Optional<Macros> macrosEsperadosOpt = macrosRepository.findById(pacienteId);

        // Caso não encontre o paciente, retornar 404
        if (macrosEsperadosOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Macros macrosEsperados = macrosEsperadosOpt.get();

        // Calcular o resultado
        Resultado resultado = resultadoService.calcularResultado(macrosEsperados, inputUsuario);

        // Retornar a mensagem formatada do resultado (toString do Resultado)
        return ResponseEntity.ok(resultado.toString());
    }
}
