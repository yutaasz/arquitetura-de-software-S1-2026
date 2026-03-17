package com.fag.lucasmartins.arquitetura_software.service;

import com.fag.lucasmartins.arquitetura_software.model.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.model.Produto;
import com.fag.lucasmartins.arquitetura_software.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService implements IProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public ProdutoBO criarProduto(ProdutoBO produtoBO) {
        Produto produto = new Produto(produtoBO.getNome(), produtoBO.getPreco(), produtoBO.getEstoque());
        produtoRepository.salvar(produto);
        
        List<Produto> todos = produtoRepository.obterTodos();
        if (todos != null && !todos.isEmpty()) {
            Produto ultimo = todos.get(todos.size() - 1);
            produtoBO.setId(ultimo.getId());
        }
        
        return produtoBO;
    }

    @Override
    public ProdutoBO obterPrimeiro() {
        List<Produto> produtos = produtoRepository.obterTodos();
        
        if (produtos == null || produtos.isEmpty()) {
            return null;
        }
        
        Produto primeiro = produtos.get(0);
        return new ProdutoBO(primeiro.getId(), primeiro.getNome(), primeiro.getPreco(), primeiro.getEstoque());
    }

    @Override
    public ProdutoBO atualizarProduto(ProdutoBO produtoBO) {
        Produto produto = produtoRepository.obterPorId(produtoBO.getId());
        
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado.");
        }
        
        produto.setNome(produtoBO.getNome());
        produto.setPreco(produtoBO.getPreco());
        produto.setEstoque(produtoBO.getEstoque());
        
        produtoRepository.atualizar(produto);
        
        return produtoBO;
    }

    @Override
    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.obterPorId(id);
        
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado.");
        }
        
        produtoRepository.deletar(id);
    }
}