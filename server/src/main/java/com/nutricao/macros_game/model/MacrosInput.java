package com.nutricao.macros_game.model;

import jakarta.persistence.*;

@Entity
@Table(name = "macros_input")
public class MacrosInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "proteina", nullable = false)
    private Double proteina;

    @Column(name = "carboidrato", nullable = false)
    private Double carboidrato;

    @Column(name = "lipidio", nullable = false)
    private Double lipidio;

    @Column(name = "kcalTotais", nullable = false)
    private Double kcalTotais;

    @Column(name = "flagKcal", nullable = false)
    private Boolean flagKcal;

    @Column(name = "data_criacao", nullable = false)
    private java.time.LocalDateTime dataCriacao;

    public MacrosInput() {
        this.dataCriacao = java.time.LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFlagKcal() {
        return flagKcal;
    }

    public void setFlagKcal(Boolean flagKcal) {
        this.flagKcal = flagKcal;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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

    public Double getKcalTotais() {
        return kcalTotais;
    }

    public void setKcalTotais(Double kcalTotais) {
        this.kcalTotais = kcalTotais;
    }

    public java.time.LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(java.time.LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
