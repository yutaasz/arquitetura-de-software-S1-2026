package com.fag.lucasmartins.arquitetura_software.repository;

import com.fag.lucasmartins.arquitetura_software.model.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProdutoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvar(Produto produto) {
        String sql = "INSERT INTO produto (nome, preco, preco_final, estoque) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, produto.getNome(), produto.getPreco(), produto.getPrecoFinal(), produto.getEstoque());
    }

    @Override
    public List<Produto> obterTodos() {
        String sql = "SELECT id, nome, preco, preco_final, estoque FROM produto";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Produto produto = new Produto(rs.getString("nome"), rs.getDouble("preco"), rs.getInt("estoque"));
            produto.setId(rs.getLong("id"));
            return produto;
        });
    }

    @Override
    public Produto obterPorId(Long id) {
        String sql = "SELECT id, nome, preco, preco_final, estoque FROM produto WHERE id = ?";
        List<Produto> result = jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> {
            Produto produto = new Produto(rs.getString("nome"), rs.getDouble("preco"), rs.getInt("estoque"));
            produto.setId(rs.getLong("id"));
            return produto;
        });
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void atualizar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, preco_final = ?, estoque = ? WHERE id = ?";
        jdbcTemplate.update(sql, produto.getNome(), produto.getPreco(), produto.getPrecoFinal(), produto.getEstoque(), produto.getId());
    }

    @Override
    public void deletar(Long id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}