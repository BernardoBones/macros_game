package com.nutricao.macros_game.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = true, length = 50)
    private String nome;

    @Column(name = "idade", nullable = false)
    private Integer idade;

    @Column(name = "peso", nullable = false)
    private Double peso;

    @Column(name = "altura", nullable = false)
    private Integer altura;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "atividadeFisica", nullable = false)
    private Double atividadeFisica;

    @Column(name = "TMB", nullable = false)
    private Double TMB;

    @Column(name = "VET", nullable = false)
    private Double VET;

    @Column(name = "dataCriacao", nullable = false)
    private java.time.LocalDateTime dataCriacao;

    public Paciente() {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getAtividadeFisica() {
        return atividadeFisica;
    }

    public void setAtividadeFisica(Double atividadeFisica) {
        this.atividadeFisica = atividadeFisica;
    }

    public Double getTMB() {
        return TMB;
    }

    public void setTMB(Double TMB) {
        this.TMB = TMB;
    }

    public Double getVET() {
        return VET;
    }

    public void setVET(Double VET) {
        this.VET = VET;
    }
}
