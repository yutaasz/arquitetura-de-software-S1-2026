package com.fag.lucasmartins.arquitetura_software.model;

import java.util.Objects;

public class Produto {
    private Long id;
    private String nome;
    private double preco;
    private double precoFinal;
    private int estoque;

    public Produto(String nome, double preco, int estoque) {
        Objects.requireNonNull(nome, "Nome não pode ser null");
        
        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        
        this.nome = nome.trim();
        this.preco = preco;
        this.estoque = estoque;
        this.precoFinal = calcularPrecoFinal();
    }

    public void validar() {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser null ou vazio");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        
        // Validação específica para produtos premium
        if (nome.toLowerCase().contains("premium")) {
            if (preco < 100.0) {
                throw new IllegalArgumentException("Produtos Premium não podem custar menos de R$ 100,00.");
            }
        }
    }

    private double calcularPrecoFinal() {
        if (estoque >= 50) {
            return preco - (preco * 0.10); // 10% desconto
        }
        return preco;
    }

    // Getters e Setters
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
        Objects.requireNonNull(nome, "Nome não pode ser null");
        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nome = nome.trim();
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        this.preco = preco;
        this.precoFinal = calcularPrecoFinal();
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        this.estoque = estoque;
        this.precoFinal = calcularPrecoFinal();
    }
}