package com.nutricao.macros_game.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "macros_input")
public class MacrosInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "proteina", nullable = false)
    private Double proteina;

    @Column(name = "carboidrato", nullable = false)
    private Double carboidrato;

    @Column(name = "lipidio", nullable = false)
    private Double lipidio;

    @Column(name = "caloriasTotais", nullable = false)
    private Double caloriasTotais;

    @Column(name = "dataCriacao", nullable = false)
    private java.time.LocalDateTime dataCriacao;

    public MacrosInput() {
        this.dataCriacao = java.time.LocalDateTime.now(); // Preenche a data automaticamente
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getProteina() {
        return proteina;
    }

    public void setProteina(Double proteina) {
        this.proteina = proteina;
    }

    public Double getCarboidrato() {
        return carboidrato;
    }

    public void setCarboidrato(Double carboidrato) {
        this.carboidrato = carboidrato;
    }

    public Double getLipidio() {
        return lipidio;
    }

    public void setLipidio(Double lipidio) {
        this.lipidio = lipidio;
    }

    public Double getCaloriasTotais() {
        return caloriasTotais;
    }

    public void setCaloriasTotais(Double caloriasTotais) {
        this.caloriasTotais = caloriasTotais;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}