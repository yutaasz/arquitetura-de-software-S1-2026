package com.fag.lucasmartins.arquitetura_software.controller;

import com.fag.lucasmartins.arquitetura_software.controller.mapper.ProdutoDTOMapper;
import com.fag.lucasmartins.arquitetura_software.dto.ProdutoRequestDTO;
import com.fag.lucasmartins.arquitetura_software.dto.ProdutoResponseDTO;
import com.fag.lucasmartins.arquitetura_software.model.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.service.IProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final IProdutoService produtoService;

    public ProdutoController(IProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody ProdutoRequestDTO dto) {
        ProdutoBO produtoBO = ProdutoDTOMapper.toBO(dto);
        
        ProdutoBO produtoCriadoBO = produtoService.criarProduto(produtoBO);
        
        ProdutoResponseDTO produtoCriadoDTO = ProdutoDTOMapper.toDTOWithId(produtoCriadoBO);

        return ResponseEntity.status(201).body(produtoCriadoDTO);
    }

    @GetMapping
    public ResponseEntity<ProdutoResponseDTO> obterTodos() {
        ProdutoBO primeiroBO = produtoService.obterPrimeiro();
        
        if (primeiroBO == null) {
            return ResponseEntity.status(204).build();
        }

        ProdutoResponseDTO responseDTO = ProdutoDTOMapper.toDTOWithId(primeiroBO);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto) {
        ProdutoBO produtoBO = ProdutoDTOMapper.toBO(dto);
        produtoBO.setId(id);

        ProdutoBO produtoAtualizadoBO = produtoService.atualizarProduto(produtoBO);

        ProdutoResponseDTO produtoAtualizadoDTO = ProdutoDTOMapper.toDTOWithId(produtoAtualizadoBO);

        return ResponseEntity.ok(produtoAtualizadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);

        return ResponseEntity.status(204).build();
    }
}