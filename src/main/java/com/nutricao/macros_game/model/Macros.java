package com.nutricao.macros_game.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import java.time.LocalDateTime;

@Entity
@Table(name = "macros")
public class Macros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pacienteId", nullable = false)
    private Paciente paciente;

    @JoinColumn(name = "proteinaMinGramas", nullable = false)
    private Double proteinaMinGramas;

    @JoinColumn(name = "proteinaMaxGramas", nullable = false)
    private Double proteinaMaxGramas;

    @JoinColumn(name = "carboidratoMinGramas", nullable = false)
    private Double carboidratoMinGramas;

    @JoinColumn(name = "carboidratoMaxGramas", nullable = false)
    private Double carboidratoMaxGramas;

    @JoinColumn(name = "lipidioMinGramas", nullable = false)
    private Double lipidioMinGramas;

    @JoinColumn(name = "lipidioMaxGramas", nullable = false)
    private Double lipidioMaxGramas;

    @JoinColumn(name = "proteinaMinKcal", nullable = false)
    private Double proteinaMinKcal;

    @JoinColumn(name = "proteinaMaxKcal", nullable = false)
    private Double proteinaMaxKcal;

    @JoinColumn(name = "carboidratoMinKcal", nullable = false)
    private Double carboidratoMinKcal;

    @JoinColumn(name = "carboidratoMaxKcal", nullable = false)
    private Double carboidratoMaxKcal;

    @JoinColumn(name = "lipidioMinKcal", nullable = false)
    private Double lipidioMinKcal;

    @JoinColumn(name = "lipidioMaxKcal", nullable = false)
    private Double lipidioMaxKcal;

    @JoinColumn(name = "dataCriacao", nullable = false)
    private java.time.LocalDateTime dataCriacao;

    public Macros() {
        this.dataCriacao = java.time.LocalDateTime.now();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Double getProteinaMinGramas() {
        return proteinaMinGramas;
    }

    public void setProteinaMinGramas(Double proteinaMinGramas) {
        this.proteinaMinGramas = proteinaMinGramas;
    }

    public Double getProteinaMaxGramas() {
        return proteinaMaxGramas;
    }

    public void setProteinaMaxGramas(Double proteinaMaxGramas) {
        this.proteinaMaxGramas = proteinaMaxGramas;
    }

    public Double getCarboidratoMinGramas() {
        return carboidratoMinGramas;
    }

    public void setCarboidratoMinGramas(Double carboidratoMinGramas) {
        this.carboidratoMinGramas = carboidratoMinGramas;
    }

    public Double getCarboidratoMaxGramas() {
        return carboidratoMaxGramas;
    }

    public void setCarboidratoMaxGramas(Double carboidratoMaxGramas) {
        this.carboidratoMaxGramas = carboidratoMaxGramas;
    }

    public Double getLipidioMinGramas() {
        return lipidioMinGramas;
    }

    public void setLipidioMinGramas(Double lipidioMinGramas) {
        this.lipidioMinGramas = lipidioMinGramas;
    }

    public Double getLipidioMaxGramas() {
        return lipidioMaxGramas;
    }

    public void setLipidioMaxGramas(Double lipidioMaxGramas) {
        this.lipidioMaxGramas = lipidioMaxGramas;
    }

    public Double getProteinaMinKcal() {
        return proteinaMinKcal;
    }

    public void setProteinaMinKcal(Double proteinaMinKcal) {
        this.proteinaMinKcal = proteinaMinKcal;
    }

    public Double getProteinaMaxKcal() {
        return proteinaMaxKcal;
    }

    public void setProteinaMaxKcal(Double proteinaMaxKcal) {
        this.proteinaMaxKcal = proteinaMaxKcal;
    }

    public Double getCarboidratoMinKcal() {
        return carboidratoMinKcal;
    }

    public void setCarboidratoMinKcal(Double carboidratoMinKcal) {
        this.carboidratoMinKcal = carboidratoMinKcal;
    }

    public Double getCarboidratoMaxKcal() {
        return carboidratoMaxKcal;
    }

    public void setCarboidratoMaxKcal(Double carboidratoMaxKcal) {
        this.carboidratoMaxKcal = carboidratoMaxKcal;
    }

    public Double getLipidioMinKcal() {
        return lipidioMinKcal;
    }

    public void setLipidioMinKcal(Double lipidioMinKcal) {
        this.lipidioMinKcal = lipidioMinKcal;
    }

    public Double getLipidioMaxKcal() {
        return lipidioMaxKcal;
    }

    public void setLipidioMaxKcal(Double lipidioMaxKcal) {
        this.lipidioMaxKcal = lipidioMaxKcal;
    }
}
