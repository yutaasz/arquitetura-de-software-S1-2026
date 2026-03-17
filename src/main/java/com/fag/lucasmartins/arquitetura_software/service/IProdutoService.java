package com.fag.lucasmartins.arquitetura_software.service;

import com.fag.lucasmartins.arquitetura_software.model.Produto;
import java.util.List;

public interface IProdutoService {
    List<Produto> obterTodosProdutos();
    Produto cadastrarProduto(String nome, double preco, int estoque);
    Produto atualizarProduto(Long id, String nome, double preco, int estoque);
    boolean deletarProduto(Long id);
}