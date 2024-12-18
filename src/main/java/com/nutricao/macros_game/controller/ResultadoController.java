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
    public ResponseEntity<String> calcularResultado(@PathVariable Long macrosInputId) {
        try {
            // Busca o objeto MacrosInput com base no ID fornecido
            Optional<MacrosInput> inputUsuarioOpt = macrosInputRepository.findById(macrosInputId);

            // Verifica se o input foi encontrado
            if (inputUsuarioOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("MacrosInput com ID " + macrosInputId + " não encontrado.");
            }

            MacrosInput inputUsuario = inputUsuarioOpt.get();

            // Buscar as macros esperadas no banco com base no pacienteId do input
            Long pacienteId = inputUsuario.getPaciente().getId();
            Optional<Macros> macrosEsperadosOpt = macrosRepository.findByPacienteId(pacienteId);

            // Verifica se as macros esperadas foram encontradas
            if (macrosEsperadosOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Macros esperados para o paciente com ID " + pacienteId + " não encontrados.");
            }

            Macros macrosEsperados = macrosEsperadosOpt.get();

            // Calcula o resultado
            Resultado resultado = resultadoService.calcularResultado(macrosEsperados, inputUsuario);

            // Salva o resultado no input e atualiza o banco
            //inputUsuario.set(resultado);
            //macrosInputRepository.save(inputUsuario);

            // Retorna a mensagem formatada do resultado
            return ResponseEntity.ok(resultado.toString());
        } catch (Exception e) {
            // Tratamento genérico de erro
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao calcular o resultado: " + e.getMessage());
        }
    }

}
