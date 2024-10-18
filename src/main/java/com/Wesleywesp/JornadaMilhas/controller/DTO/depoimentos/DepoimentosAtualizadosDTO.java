package com.Wesleywesp.JornadaMilhas.controller.DTO.depoimentos;

import com.Wesleywesp.JornadaMilhas.domain.Depoimento;

public record DepoimentosAtualizadosDTO(String nome,
                                        String foto,
                                        String depoimento) {
    public DepoimentosAtualizadosDTO(Depoimento dados) {
        this(dados.getNome(), dados.getDepoimento(), dados.getFoto());
    }
}
