package com.fag.lucasmartins.arquitetura_software.model.bo;

public class ProdutoBO {

    private Long id;
    private String nome;
    private double preco;
    private int estoque;
    private double precoFinal;

    public ProdutoBO(String nome, double preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.precoFinal = calcularPrecoFinal();
        validar();
    }

    public ProdutoBO(Long id, String nome, double preco, int estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.precoFinal = calcularPrecoFinal();
        validar();
    }

    public void validar() {
        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Nome do produto é obrigatório.");
        }
        if (preco <= 0) {
            throw new RuntimeException("Preço do produto deve ser maior que zero.");
        }
        if (estoque < 0) {
            throw new RuntimeException("Estoque não pode ser negativo.");
        }
        if (nome.toLowerCase().contains("premium")) {
            if (preco < 100.0) {
                throw new RuntimeException("Produtos Premium não podem custar menos de R$ 100,00.");
            }
        }
    }

    private double calcularPrecoFinal() {
        if (estoque >= 50) {
            return preco - (preco * 0.10);
        }
        return preco;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
        this.precoFinal = calcularPrecoFinal();
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
        this.precoFinal = calcularPrecoFinal();
    }

    public double getPrecoFinal() {
        return precoFinal;
    }
}
