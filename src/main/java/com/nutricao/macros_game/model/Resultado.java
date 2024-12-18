package com.nutricao.macros_game.model;

public class Resultado {
    private Boolean adeqProteina;
    private Boolean adeqCarboidrato;
    private Boolean adeqLipidio;
    private Boolean adeqKcalTotais;
    private Boolean aprovado;

    public Resultado() {}

    public Resultado(Boolean adeqProteina, Boolean adeqCarboidrato, Boolean adeqLipidio, Boolean adeqKcalTotais, Boolean aprovado) {
        this.adeqProteina = adeqProteina;
        this.adeqCarboidrato = adeqCarboidrato;
        this.adeqLipidio = adeqLipidio;
        this.adeqKcalTotais = adeqKcalTotais;
        this.aprovado = aprovado;
    }

    public Boolean getAprovado() {
        return aprovado;
    }

    @Override
    public String toString() {
        if (aprovado) {
            return "Parabéns! Você acertou todos os macros e calorias.";
        }

        StringBuilder erros = new StringBuilder("Você não foi aprovado. Veja onde errou:\n");

        if (!adeqProteina) {
            erros.append("- Proteína: Fora da faixa esperada.\n");
        }
        if (!adeqCarboidrato) {
            erros.append("- Carboidrato: Fora da faixa esperada.\n");
        }
        if (!adeqLipidio) {
            erros.append("- Lipídio: Fora da faixa esperada.\n");
        }
        if (!adeqKcalTotais) {
            erros.append("- Calorias Totais: Fora da faixa de 95% a 105%.\n");
        }

        return erros.toString();
    }
}
