package com.Wesleywesp.JornadaMilhas.controller.DTO.depoimentos;

import com.Wesleywesp.JornadaMilhas.domain.Depoimento;

public record ListarDepoimentosDTO(Long id,
                                   String foto,
                                   String depoimento,
                                   String nome) {
    public ListarDepoimentosDTO(Depoimento depoimento) {
        this(depoimento.getId(), depoimento.getNome(), depoimento.getDepoimento()
                , depoimento.getFoto());
    }
}
