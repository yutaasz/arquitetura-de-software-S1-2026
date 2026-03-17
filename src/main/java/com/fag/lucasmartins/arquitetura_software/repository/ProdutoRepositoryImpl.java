package com.fag.lucasmartins.arquitetura_software.repository;

import com.fag.lucasmartins.arquitetura_software.model.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoRepositoryImpl.class);
    private final JdbcTemplate jdbcTemplate;

    public ProdutoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = Objects.requireNonNull(jdbcTemplate, "JdbcTemplate não pode ser null");
    }

    @Override
    public void salvar(Produto produto) {
        Objects.requireNonNull(produto, "Produto não pode ser null");
        
        logger.info("Salvando produto: {}", produto.getNome());
        try {
            String sql = "INSERT INTO produto (nome, preco, preco_final, estoque) VALUES (?, ?, ?, ?)";
            int resultado = jdbcTemplate.update(sql, 
                produto.getNome(), 
                produto.getPreco(), 
                produto.getPrecoFinal(), 
                produto.getEstoque()
            );
            
            if (resultado <= 0) {
                logger.error("Falha ao inserir produto: {}", produto.getNome());
                throw new RuntimeException("Falha ao inserir produto no banco de dados");
            }
            
            logger.info("Produto salvo com sucesso: {}", produto.getNome());
        } catch (Exception e) {
            logger.error("Erro ao salvar produto", e);
            throw new RuntimeException("Erro ao salvar produto no banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Produto> obterTodos() {
        logger.info("Obtendo todos os produtos");
        try {
            String sql = "SELECT id, nome, preco, preco_final, estoque FROM produto";
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                Produto produto = new Produto(rs.getString("nome"), rs.getDouble("preco"), rs.getInt("estoque"));
                produto.setId(rs.getLong("id"));
                return produto;
            });
        } catch (Exception e) {
            logger.error("Erro ao obter todos os produtos", e);
            throw new RuntimeException("Erro ao obter produtos do banco de dados", e);
        }
    }

    @Override
    public Produto obterPorId(Long id) {
        Objects.requireNonNull(id, "ID não pode ser null");
        
        logger.info("Obtendo produto com ID: {}", id);
        try {
            String sql = "SELECT id, nome, preco, preco_final, estoque FROM produto WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                Produto produto = new Produto(rs.getString("nome"), rs.getDouble("preco"), rs.getInt("estoque"));
                produto.setId(rs.getLong("id"));
                return produto;
            });
        } catch (Exception e) {
            logger.warn("Produto com ID {} não encontrado", id);
            return null;
        }
    }

    @Override
    public void atualizar(Produto produto) {
        Objects.requireNonNull(produto, "Produto não pode ser null");
        
        logger.info("Atualizando produto: {}", produto.getNome());
        try {
            String sql = "UPDATE produto SET nome = ?, preco = ?, preco_final = ?, estoque = ? WHERE id = ?";
            int resultado = jdbcTemplate.update(sql, 
                produto.getNome(), 
                produto.getPreco(), 
                produto.getPrecoFinal(), 
                produto.getEstoque(),
                produto.getId()
            );
            
            if (resultado <= 0) {
                logger.error("Falha ao atualizar produto com ID: {}", produto.getId());
                throw new RuntimeException("Falha ao atualizar produto no banco de dados");
            }
            
            logger.info("Produto atualizado com sucesso: {}", produto.getNome());
        } catch (Exception e) {
            logger.error("Erro ao atualizar produto", e);
            throw new RuntimeException("Erro ao atualizar produto no banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletar(Long id) {
        Objects.requireNonNull(id, "ID não pode ser null");
        
        logger.info("Deletando produto com ID: {}", id);
        try {
            String sql = "DELETE FROM produto WHERE id = ?";
            int resultado = jdbcTemplate.update(sql, id);
            
            if (resultado <= 0) {
                logger.error("Falha ao deletar produto com ID: {}", id);
                throw new RuntimeException("Falha ao deletar produto do banco de dados");
            }
            
            logger.info("Produto deletado com sucesso: {}", id);
        } catch (Exception e) {
            logger.error("Erro ao deletar produto", e);
            throw new RuntimeException("Erro ao deletar produto do banco de dados: " + e.getMessage(), e);
        }
    }
}