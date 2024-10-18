package com.Wesleywesp.JornadaMilhas.controller.DTO.destino;

import com.Wesleywesp.JornadaMilhas.domain.Destino;

import java.math.BigDecimal;

public record DetalharDestinos(Long id,
                               String nome,
                               String foto,
                               BigDecimal preco) {
   public DetalharDestinos(Destino dados){
        this(dados.getId(), dados.getFoto(),dados.getNome(), dados.getPreco());
    }
}
