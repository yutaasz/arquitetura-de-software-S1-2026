package com.fag.lucasmartins.arquitetura_software.dto;

public class ProdutoResponseDTO {
    private Long id;
    private String mensagem;
    private String nome;
    private int estoque;
    private double preco;
    private double precoFinal;

    public ProdutoResponseDTO() {}

    public ProdutoResponseDTO(String mensagem, String nome, int estoque, double preco, double precoFinal) {
        this.mensagem = mensagem;
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
        this.precoFinal = precoFinal;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }
}