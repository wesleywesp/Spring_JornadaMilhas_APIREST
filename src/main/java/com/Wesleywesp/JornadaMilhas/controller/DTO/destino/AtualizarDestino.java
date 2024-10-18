package com.Wesleywesp.JornadaMilhas.controller.DTO.destino;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AtualizarDestino(@NotNull
                               Long id,
                               String nome,
                               String foto,
                               BigDecimal preco) {
}
