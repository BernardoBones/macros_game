package com.nutricao.macros_game.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;
    private Double peso;
    private Integer altura;
    private String genero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAtividadeFisica() {
        return atividadeFisica;
    }

    public void setAtividadeFisica(Double atividadeFisica) {
        this.atividadeFisica = atividadeFisica;
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

    private Double atividadeFisica;
    private Double TMB;
    private Double VET;
}
