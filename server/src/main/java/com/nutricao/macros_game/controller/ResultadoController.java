package com.nutricao.macros_game.controller;

import com.nutricao.macros_game.model.Macros;
import com.nutricao.macros_game.model.MacrosInput;
import com.nutricao.macros_game.model.Resultado;
import com.nutricao.macros_game.repository.MacrosInputRepository;
import com.nutricao.macros_game.repository.MacrosRepository;
import com.nutricao.macros_game.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/resultados")
public class ResultadoController {

    @Autowired
    private MacrosRepository macrosRepository;

    @Autowired
    private ResultadoService resultadoService;
    @Autowired
    private MacrosInputRepository macrosInputRepository;

    @GetMapping("/{macrosInputId}")
    public ResponseEntity<?> calcularResultado(@PathVariable Long macrosInputId) {
        try {
            // Busca o objeto MacrosInput com base no ID fornecido
            Optional<MacrosInput> inputUsuarioOpt = macrosInputRepository.findById(macrosInputId);

            if (inputUsuarioOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("MacrosInput com ID " + macrosInputId + " não encontrado.");
            }

            MacrosInput inputUsuario = inputUsuarioOpt.get();

            // Buscar as macros esperadas no banco com base no pacienteId do input
            Long pacienteId = inputUsuario.getPaciente().getId();
            Optional<Macros> macrosEsperadosOpt = macrosRepository.findByPacienteId(pacienteId);

            if (macrosEsperadosOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Macros esperados para o paciente com ID " + pacienteId + " não encontrados.");
            }

            Macros macrosEsperados = macrosEsperadosOpt.get();

            // Calcula o resultado
            Resultado resultado = resultadoService.calcularResultado(macrosEsperados, inputUsuario);

            Map<String, Boolean> resultadoMap = new HashMap<>();
            resultadoMap.put("aprovado", resultado.getAprovado());
            resultadoMap.put("adequacaoCaloriasTotais", resultado.getAdeqKcalTotais());
            resultadoMap.put("adequacaoProteina", resultado.getAdeqProteina());
            resultadoMap.put("adequacaoCarboidrato", resultado.getAdeqCarboidrato());
            resultadoMap.put("adequacaoLipidio", resultado.getAdeqLipidio());

            return ResponseEntity.ok(resultadoMap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao calcular o resultado: " + e.getMessage());
        }
    }


}
