package com.Wesleywesp.JornadaMilhas.controller.DTO.destino;

import com.Wesleywesp.JornadaMilhas.domain.Destino;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

public record DetalharDestino(Long id,
                              String nome,
                              String foto,
                              String foto2,
                              String meta,
                              String descritivo) {

    public DetalharDestino(Destino dest, String descritivo) {
        this(dest.getId(), dest.getNome(), dest.getFoto(), dest.getFoto2(), dest.getMeta(), descritivo);
    }
}
