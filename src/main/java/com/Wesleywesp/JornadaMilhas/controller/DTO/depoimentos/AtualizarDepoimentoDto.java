package com.Wesleywesp.JornadaMilhas.controller.DTO.depoimentos;

import jakarta.validation.constraints.NotNull;

public record AtualizarDepoimentoDto(@NotNull Long id,
                                     String foto,
                                     String depoimento,
                                     String nome) {
}
