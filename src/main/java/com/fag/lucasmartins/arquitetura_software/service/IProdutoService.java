package com.fag.lucasmartins.arquitetura_software.service;

import com.fag.lucasmartins.arquitetura_software.model.bo.ProdutoBO;

public interface IProdutoService {
    ProdutoBO criarProduto(ProdutoBO produtoBO);
    ProdutoBO obterPrimeiro();
    ProdutoBO atualizarProduto(ProdutoBO produtoBO);
    void deletarProduto(Long id);
}