package com.fag.lucasmartins.arquitetura_software.repository;

import com.fag.lucasmartins.arquitetura_software.model.Produto;
import java.util.List;

public interface ProdutoRepository {
    void salvar(Produto produto);
    List<Produto> obterTodos();
    Produto obterPorId(Long id);
    void atualizar(Produto produto);
    void deletar(Long id);
}