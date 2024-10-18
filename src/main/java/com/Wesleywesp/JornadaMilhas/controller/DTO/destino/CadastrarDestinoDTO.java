package com.Wesleywesp.JornadaMilhas.controller.DTO.destino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CadastrarDestinoDTO (@NotBlank
                                   String nome,
                                  @NotBlank
                                  String foto,
                                  @NotNull
                                  BigDecimal preco){
}
