package com.Wesleywesp.JornadaMilhas.controller.DTO.depoimentos;

import jakarta.validation.constraints.NotBlank;

public record getDepoimentoDto(@NotBlank String foto,
                               @NotBlank
                               String depoimento,
                               @NotBlank
                               String nome) {
}
