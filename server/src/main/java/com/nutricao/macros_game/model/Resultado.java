package com.nutricao.macros_game.model;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resultado {
    private Boolean adeqProteina;

    public Boolean getAdeqProteina() {
        return adeqProteina;
    }

    public Boolean getAdeqCarboidrato() {
        return adeqCarboidrato;
    }

    public Boolean getAdeqLipidio() {
        return adeqLipidio;
    }

    public Boolean getAdeqKcalTotais() {
        return adeqKcalTotais;
    }

    public Boolean getAprovado() {
        return aprovado;
    }

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
}
