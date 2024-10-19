package com.Wesleywesp.JornadaMilhas.controller.DTO.destino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CadastrarDestinoDTO (@NotBlank
                                   String nome,
                                  @NotBlank
                                  String foto,
                                  @NotBlank
                                  String foto2,
                                  @NotNull
                                  BigDecimal preco,
                                   @NotBlank
                                   @Size(max = 160)
                                   String meta,
                                   String descritivo){
}
