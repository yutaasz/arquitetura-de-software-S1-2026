package com.fag.lucasmartins.arquitetura_software.service;

import com.fag.lucasmartins.arquitetura_software.model.Produto;
import com.fag.lucasmartins.arquitetura_software.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

@Service
public class ProdutoService implements IProdutoService {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = Objects.requireNonNull(produtoRepository, "ProdutoRepository não pode ser null");
    }

    @Override
    public List<Produto> obterTodosProdutos() {
        logger.info("Obtendo todos os produtos");
        try {
            List<Produto> produtos = produtoRepository.obterTodos();
            return produtos != null ? produtos : List.of();
        } catch (Exception e) {
            logger.error("Erro ao obter todos os produtos", e);
            throw new RuntimeException("Erro ao obter produtos do banco de dados", e);
        }
    }

    @Override
    public Produto cadastrarProduto(String nome, double preco, int estoque) {
        logger.info("Cadastrando produto: {}", nome);
        
        Objects.requireNonNull(nome, "Nome do produto não pode ser null");
        
        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }

        try {
            Produto produto = new Produto(nome.trim(), preco, estoque);
            produto.validar();
            produtoRepository.salvar(produto);
            logger.info("Produto cadastrado com sucesso: {}", nome);
            return produto;
        } catch (IllegalArgumentException e) {
            logger.warn("Validação falhou ao cadastrar produto: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Erro ao cadastrar produto", e);
            throw new RuntimeException("Erro ao salvar produto no banco de dados", e);
        }
    }

    @Override
    public Produto atualizarProduto(Long id, String nome, double preco, int estoque) {
        logger.info("Atualizando produto com ID: {}", id);
        
        Objects.requireNonNull(id, "ID do produto não pode ser null");
        Objects.requireNonNull(nome, "Nome do produto não pode ser null");
        
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero");
        }
        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }

        try {
            Produto produto = produtoRepository.obterPorId(id);
            
            if (produto == null) {
                logger.warn("Produto com ID {} não encontrado", id);
                return null;
            }

            produto.setNome(nome.trim());
            produto.setPreco(preco);
            produto.setEstoque(estoque);
            produto.validar();
            
            produtoRepository.atualizar(produto);
            logger.info("Produto atualizado com sucesso: {}", id);
            return produto;
        } catch (IllegalArgumentException e) {
            logger.warn("Validação falhou ao atualizar produto: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Erro ao atualizar produto", e);
            throw new RuntimeException("Erro ao atualizar produto no banco de dados", e);
        }
    }

    @Override
    public boolean deletarProduto(Long id) {
        logger.info("Deletando produto com ID: {}", id);
        
        Objects.requireNonNull(id, "ID do produto não pode ser null");
        
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero");
        }

        try {
            Produto produto = produtoRepository.obterPorId(id);
            
            if (produto == null) {
                logger.warn("Produto com ID {} não encontrado para deletar", id);
                return false;
            }

            produtoRepository.deletar(id);
            logger.info("Produto deletado com sucesso: {}", id);
            return true;
        } catch (Exception e) {
            logger.error("Erro ao deletar produto", e);
            throw new RuntimeException("Erro ao deletar produto do banco de dados", e);
        }
    }
}