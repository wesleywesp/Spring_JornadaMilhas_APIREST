package com.Wesleywesp.JornadaMilhas.controller.DTO;


import com.Wesleywesp.JornadaMilhas.controller.DTO.destino.AtualizarDestino;
import com.Wesleywesp.JornadaMilhas.controller.DTO.destino.CadastrarDestinoDTO;
import com.Wesleywesp.JornadaMilhas.controller.DTO.destino.DadosAtualizados;
import com.Wesleywesp.JornadaMilhas.controller.DTO.destino.DetalharDestinos;
import com.Wesleywesp.JornadaMilhas.domain.Destino;
import com.Wesleywesp.JornadaMilhas.domain.ValidacaoException;
import com.Wesleywesp.JornadaMilhas.repository.DestinoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class DestinoController {
    @Autowired
    private DestinoRepository destinoRepository;

    @PostMapping("destinos")
    @Transactional
    public ResponseEntity cadastrarDestino(@RequestBody CadastrarDestinoDTO dados, UriComponentsBuilder uriBuilder){
        var destino = new Destino(dados);
         destinoRepository.save(destino);
         var uri = uriBuilder.path("destinos/{id}").buildAndExpand(destino.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalharDestinos(destino));
    }
    @GetMapping("destinos")
    @Transactional
    public ResponseEntity<?>todosOSDEstino(
            @RequestParam(required = false) String nome,  // Parâmetro opcional 'nome'
            @PageableDefault(size = 10, sort = {"nome"}) Pageable pagina){
        if(nome != null) {
            var destino = destinoRepository.getReferenceByNome(nome);
            if(destino != null) {
                return ResponseEntity.ok(new DetalharDestinos(destino));
            }

            throw new ValidacaoException("Nenhum destino foi encontrado");
        }
        // Se o parâmetro 'nome' não for fornecido, faz a listagem paginada
        var page = destinoRepository.findAllByAtivoTrue(pagina).map(DetalharDestinos::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping("destinos")
    @Transactional
    public ResponseEntity atualizar(@RequestBody AtualizarDestino dados){
        var destino = destinoRepository.getReferenceById(dados.id());
        destino.atualizar(dados);
        return ResponseEntity.ok(new DadosAtualizados(dados));
    }
    @DeleteMapping("destinos/excluir/{id}")//Excluir do banco de dados
    @Transactional
    public ResponseEntity Excluir(@PathVariable Long id){
        var destino = destinoRepository.getReferenceById(id);

        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("destinos/desativar/{id}")//Desativar apenas
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id){
        var destino = destinoRepository.getReferenceById(id);
        destino.desativar();

        return ResponseEntity.noContent().build();
    }

}
