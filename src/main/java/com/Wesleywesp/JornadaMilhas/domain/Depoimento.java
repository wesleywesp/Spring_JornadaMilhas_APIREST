package com.Wesleywesp.JornadaMilhas.domain;

import com.Wesleywesp.JornadaMilhas.controller.DTO.depoimentos.AtualizarDepoimentoDto;
import com.Wesleywesp.JornadaMilhas.controller.DTO.depoimentos.getDepoimentoDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
@Entity(name ="Depoimento")
@Table(name ="depoimentos")
public class

Depoimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foto;
    private String depoimento;
    private String nome;
    private Boolean ativo;

    public Depoimento(getDepoimentoDto dados) {
        this.ativo = true;
        this.foto=dados.foto();
        this.depoimento = dados.depoimento();
        this.nome = dados.nome();
    }

    public void atualizarDepoimentos(AtualizarDepoimentoDto dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.depoimento() != null) {
            this.depoimento = dados.depoimento();
        }
        if (dados.foto() != null) {
            this.foto=dados.foto();
        }
    }

    public void deletardepoimento() {
        this.ativo= false;
    }
}
