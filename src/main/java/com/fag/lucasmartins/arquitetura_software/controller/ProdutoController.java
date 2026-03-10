package com.fag.lucasmartins.arquitetura_software.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping
    public ResponseEntity<Object> cadastrarProduto(@RequestBody Map<String, Object> payload) {
        try {
            String nome = (String) payload.get("nome");
            Integer estoque = (Integer) payload.get("estoque");
            double preco = (double) payload.get("preco");

            if (nome != null && nome.toLowerCase().contains("premium")) {
                if (preco < 100.0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Produtos Premium não podem custar menos de R$ 100,00.");
                }
            }
            double precoFinal = preco;
            if (estoque != null && estoque >= 50) {
                precoFinal = preco - (preco * 0.10); // 10%
            }

            String sqlInsertPedido = "INSERT INTO produto (nome, preco, preco_final, estoque) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sqlInsertPedido, nome, preco, precoFinal, estoque);

            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Produto cadastrado com sucesso!");
            response.put("nome", nome);
            response.put("estoque", estoque);
            response.put("preco", preco);
            response.put("preco_final", precoFinal);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar requisição: " + e.getMessage());
        }
    }
}