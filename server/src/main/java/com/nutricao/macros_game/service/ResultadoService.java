package com.nutricao.macros_game.service;

import com.nutricao.macros_game.model.Resultado;
import com.nutricao.macros_game.model.MacrosInput;
import com.nutricao.macros_game.model.Macros;
import org.springframework.stereotype.Service;

@Service
public class ResultadoService {

    public Resultado calcularResultado(Macros macrosEsperados, MacrosInput inputUsuario) {
        // Verifica adequação dos 3 macros
        Boolean adeqProteina = calculaAdequacaoProteina(macrosEsperados, inputUsuario);
        Boolean adeqCarboidrato = calculaAdequacaoCarboidrato(macrosEsperados, inputUsuario);
        Boolean adeqLipidio = calculaAdequacaoLipidio(macrosEsperados, inputUsuario);

        // Verifica se as kcal totais representam entre 95% e 105% do VET
        Boolean adeqKcalTotais = calculaAdequacaoKcalTotais(macrosEsperados, inputUsuario);

        // Aprovação depende de todas as condições serem verdadeiras
        Boolean aprovado = adeqProteina && adeqCarboidrato && adeqLipidio && adeqKcalTotais;
        return new Resultado(adeqProteina, adeqCarboidrato, adeqLipidio, adeqKcalTotais, aprovado);
    }

    private Boolean calculaAdequacaoProteina(Macros macrosEsperados, MacrosInput inputUsuario) {

        double minProteina;
        double maxProteina;
        double proteinaInput = inputUsuario.getProteina();

        if(inputUsuario.getFlagKcal().equals(true)){
            minProteina = macrosEsperados.getProteinaMinKcal();
            maxProteina = macrosEsperados.getProteinaMaxKcal();
        } else {
            minProteina = macrosEsperados.getProteinaMinGramas();
            maxProteina = macrosEsperados.getProteinaMaxGramas();
        }

        double difProteinaMin = proteinaInput - minProteina;
        double difProteinaMax = proteinaInput - maxProteina;

        return difProteinaMin > 0 && difProteinaMax < 0;
    }

    private Boolean calculaAdequacaoCarboidrato(Macros macrosEsperados, MacrosInput inputUsuario) {

        double minCarbo;
        double maxCarbo;
        double carboInput = inputUsuario.getCarboidrato();

        if(inputUsuario.getFlagKcal().equals(true)){
            minCarbo = macrosEsperados.getCarboidratoMinKcal();
            maxCarbo = macrosEsperados.getCarboidratoMaxKcal();
        } else {
            minCarbo = macrosEsperados.getCarboidratoMinGramas();
            maxCarbo = macrosEsperados.getCarboidratoMaxGramas();
        }

        double difCarboidratoMin = carboInput - minCarbo;
        double difCarboidratoMax = carboInput - maxCarbo;

        return difCarboidratoMin > 0 && difCarboidratoMax < 0;
    }

    private Boolean calculaAdequacaoLipidio(Macros macrosEsperados, MacrosInput inputUsuario) {

        double minLipidio;
        double maxLipidio;
        double lipidioInput = inputUsuario.getLipidio();

        if(inputUsuario.getFlagKcal().equals(true)){
            minLipidio = macrosEsperados.getLipidioMinKcal();
            maxLipidio = macrosEsperados.getLipidioMaxKcal();
        } else {
            minLipidio = macrosEsperados.getLipidioMinGramas();
            maxLipidio = macrosEsperados.getLipidioMaxGramas();
        }

        double difLipidioMin = lipidioInput - minLipidio;
        double difLipidioMax = lipidioInput - maxLipidio;

        return difLipidioMin > 0 && difLipidioMax < 0;
    }

    private Boolean calculaAdequacaoKcalTotais(Macros macrosEsperados, MacrosInput inputUsuario) {
        double kcalInput = inputUsuario.getKcalTotais();
        double vet = macrosEsperados.getPaciente().getVET();

        // Intervalo entre 95% e 105% do VET
        double limiteInferior = vet * 0.95;
        double limiteSuperior = vet * 1.05;

        return kcalInput >= limiteInferior && kcalInput <= limiteSuperior;
    }
}
