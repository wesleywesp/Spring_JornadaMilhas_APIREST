package com.Wesleywesp.JornadaMilhas.domain;

import com.Wesleywesp.JornadaMilhas.controller.DTO.destino.AtualizarDestino;
import com.Wesleywesp.JornadaMilhas.controller.DTO.destino.CadastrarDestinoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "Destino")
@Table(name = "destinos")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of= "id")
@AllArgsConstructor
public class Destino {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foto;
    private String foto2;
    private String nome;
    private BigDecimal preco;
    private String meta;
    private String descritivo;
    private Boolean ativo;

    public Destino(CadastrarDestinoDTO dados) {
        this.ativo = true;
        this.nome= dados.nome();
        this.foto = dados.foto();
        this.foto2 = dados.foto2();
        this.preco = dados.preco();
        this.meta = dados.meta();
        this.descritivo = dados.descritivo();
    }
    public void atualizar(AtualizarDestino dados){
        if(dados.foto() != null){
            this.foto= dados.foto();
        }if(dados.meta()!= null){
            this.meta= dados.meta();
        }
        if (dados.descritivo() != null){
            this.descritivo = dados.descritivo();
        }
        if(dados.foto2() != null){
            this.foto2 = dados.foto2();
        }
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.preco() != null){
            this.preco = dados.preco();
        }
    }

    public void desativar() {
        this.ativo = false;
    }
}
