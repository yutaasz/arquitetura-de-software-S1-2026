package com.fag.lucasmartins.arquitetura_software.controller.mapper;

import com.fag.lucasmartins.arquitetura_software.dto.ProdutoRequestDTO;
import com.fag.lucasmartins.arquitetura_software.dto.ProdutoResponseDTO;
import com.fag.lucasmartins.arquitetura_software.model.bo.ProdutoBO;

public class ProdutoDTOMapper {

    public static ProdutoBO toBO(ProdutoRequestDTO dto) {
        return new ProdutoBO(dto.getNome(), dto.getPreco(), dto.getEstoque());
    }

    public static ProdutoResponseDTO toDTO(ProdutoBO bo) {
        return new ProdutoResponseDTO(
            "Operação realizada com sucesso!",
            bo.getNome(),
            bo.getEstoque(),
            bo.getPreco(),
            bo.getPrecoFinal()
        );
    }

    public static ProdutoResponseDTO toDTOWithId(ProdutoBO bo) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO(
            "Operação realizada com sucesso!",
            bo.getNome(),
            bo.getEstoque(),
            bo.getPreco(),
            bo.getPrecoFinal()
        );
        dto.setId(bo.getId());
        return dto;
    }
}
