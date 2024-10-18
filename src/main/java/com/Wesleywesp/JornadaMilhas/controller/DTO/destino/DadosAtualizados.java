package com.Wesleywesp.JornadaMilhas.controller.DTO.destino;

import java.math.BigDecimal;

public record DadosAtualizados(String nome,
                               String foto,
                               BigDecimal preco) {
    public DadosAtualizados(AtualizarDestino dados){
        this(dados.foto(), dados.nome(), dados.preco());
    }
}
