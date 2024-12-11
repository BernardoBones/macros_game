package com.nutricao.macros_game.model;

import jakarta.persistence.*;

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
    private double proteinaMinGramas;

    @JoinColumn(name = "proteinaMaxGramas", nullable = false)
    private double proteinaMaxGramas;

    @JoinColumn(name = "carboidratoMinGramas", nullable = false)
    private double carboidratoMinGramas;

    @JoinColumn(name = "carboidratoMaxGramas", nullable = false)
    private double carboidratoMaxGramas;

    @JoinColumn(name = "lipidioMinGramas", nullable = false)
    private double lipidioMinGramas;

    @JoinColumn(name = "lipidioMaxGramas", nullable = false)
    private double lipidioMaxGramas;

    @JoinColumn(name = "proteinaMinKcal", nullable = false)
    private double proteinaMinKcal;

    @JoinColumn(name = "proteinaMaxKcal", nullable = false)
    private double proteinaMaxKcal;

    @JoinColumn(name = "carboidratoMinKcal", nullable = false)
    private double carboidratoMinKcal;

    @JoinColumn(name = "carboidratoMaxKcal", nullable = false)
    private double carboidratoMaxKcal;

    @JoinColumn(name = "lipidioMinKcal", nullable = false)
    private double lipidioMinKcal;

    @JoinColumn(name = "lipidioMaxKcal", nullable = false)
    private double lipidioMaxKcal;

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

    public double getProteinaMinGramas() {
        return proteinaMinGramas;
    }

    public void setProteinaMinGramas(double proteinaMinGramas) {
        this.proteinaMinGramas = proteinaMinGramas;
    }

    public double getProteinaMaxGramas() {
        return proteinaMaxGramas;
    }

    public void setProteinaMaxGramas(double proteinaMaxGramas) {
        this.proteinaMaxGramas = proteinaMaxGramas;
    }

    public double getCarboidratoMinGramas() {
        return carboidratoMinGramas;
    }

    public void setCarboidratoMinGramas(double carboidratoMinGramas) {
        this.carboidratoMinGramas = carboidratoMinGramas;
    }

    public double getCarboidratoMaxGramas() {
        return carboidratoMaxGramas;
    }

    public void setCarboidratoMaxGramas(double carboidratoMaxGramas) {
        this.carboidratoMaxGramas = carboidratoMaxGramas;
    }

    public double getLipidioMinGramas() {
        return lipidioMinGramas;
    }

    public void setLipidioMinGramas(double lipidioMinGramas) {
        this.lipidioMinGramas = lipidioMinGramas;
    }

    public double getLipidioMaxGramas() {
        return lipidioMaxGramas;
    }

    public void setLipidioMaxGramas(double lipidioMaxGramas) {
        this.lipidioMaxGramas = lipidioMaxGramas;
    }

    public double getProteinaMinKcal() {
        return proteinaMinKcal;
    }

    public void setProteinaMinKcal(double proteinaMinKcal) {
        this.proteinaMinKcal = proteinaMinKcal;
    }

    public double getProteinaMaxKcal() {
        return proteinaMaxKcal;
    }

    public void setProteinaMaxKcal(double proteinaMaxKcal) {
        this.proteinaMaxKcal = proteinaMaxKcal;
    }

    public double getCarboidratoMinKcal() {
        return carboidratoMinKcal;
    }

    public void setCarboidratoMinKcal(double carboidratoMinKcal) {
        this.carboidratoMinKcal = carboidratoMinKcal;
    }

    public double getCarboidratoMaxKcal() {
        return carboidratoMaxKcal;
    }

    public void setCarboidratoMaxKcal(double carboidratoMaxKcal) {
        this.carboidratoMaxKcal = carboidratoMaxKcal;
    }

    public double getLipidioMinKcal() {
        return lipidioMinKcal;
    }

    public void setLipidioMinKcal(double lipidioMinKcal) {
        this.lipidioMinKcal = lipidioMinKcal;
    }

    public double getLipidioMaxKcal() {
        return lipidioMaxKcal;
    }

    public void setLipidioMaxKcal(double lipidioMaxKcal) {
        this.lipidioMaxKcal = lipidioMaxKcal;
    }
}
