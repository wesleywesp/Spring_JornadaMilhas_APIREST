package com.Wesleywesp.JornadaMilhas.controller.DTO;

import com.Wesleywesp.JornadaMilhas.controller.DTO.depoimentos.AtualizarDepoimentoDto;
import com.Wesleywesp.JornadaMilhas.controller.DTO.depoimentos.DepoimentosAtualizadosDTO;
import com.Wesleywesp.JornadaMilhas.controller.DTO.depoimentos.ListarDepoimentosDTO;
import com.Wesleywesp.JornadaMilhas.controller.DTO.depoimentos.getDepoimentoDto;
import com.Wesleywesp.JornadaMilhas.domain.Depoimento;
import com.Wesleywesp.JornadaMilhas.repository.DepoimentoReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class DepoimentoController {
    @Autowired
    private DepoimentoReporitory depoimentoReporitory;

    @PostMapping("depoimento")
    public ResponseEntity cadastrarDepoimento(@RequestBody getDepoimentoDto dados, UriComponentsBuilder uriBuilder){
        var depoimento = new Depoimento(dados);
        depoimentoReporitory.save(depoimento);
        var uri = uriBuilder.path("depoimento/{id}").buildAndExpand(depoimento.getId()).toUri();

        return ResponseEntity.created(uri).body(dados);
    }
    @GetMapping("depoimento")
    public ResponseEntity<Page<ListarDepoimentosDTO>> depimentos(@PageableDefault(size = 10, sort={"nome"}) Pageable pagina ){
        var page = depoimentoReporitory.findAllByAtivoTrue(pagina).map(ListarDepoimentosDTO::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("depoimento/{id}")
    public ResponseEntity depimento(@PathVariable Long id){
        var depoimento = depoimentoReporitory.findById(id).map(ListarDepoimentosDTO::new);
        return ResponseEntity.ok(depoimento);
    }
    @PutMapping("depoimento")
    public ResponseEntity atualizar(@RequestBody AtualizarDepoimentoDto dados){
        var depoimento =depoimentoReporitory.getReferenceById(dados.id());
        depoimento.atualizarDepoimentos(dados);
       return ResponseEntity.ok(new DepoimentosAtualizadosDTO(depoimento));
    }
    @DeleteMapping("depoimento-desativar/{id}")
    public ResponseEntity desativar (@PathVariable Long id ){
        var depoimento = depoimentoReporitory.getReferenceById(id);
        depoimento.deletardepoimento();
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("depoimento-excluir/{id}")
    public ResponseEntity deletar (@PathVariable Long id ){
        var depoimento = depoimentoReporitory.getReferenceById(id);
        depoimento.deletardepoimento();
        depoimentoReporitory.delete(depoimento);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("depoimento-home")
    public ResponseEntity<Page<ListarDepoimentosDTO>> depimentosRandom(@PageableDefault(size = 3, sort={"nome"}) Pageable pagina ){
        var page = depoimentoReporitory.findAllByAtivoTrueOrderByRandom(pagina).map(ListarDepoimentosDTO::new);
        return ResponseEntity.ok(page);
    }



}
