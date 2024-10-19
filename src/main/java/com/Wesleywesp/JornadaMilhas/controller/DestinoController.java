package com.Wesleywesp.JornadaMilhas.controller;


import com.Wesleywesp.JornadaMilhas.controller.DTO.destino.*;
import com.Wesleywesp.JornadaMilhas.domain.Destino;
import com.Wesleywesp.JornadaMilhas.domain.ValidacaoException;
import com.Wesleywesp.JornadaMilhas.repository.DestinoRepository;
import com.Wesleywesp.JornadaMilhas.service.DestinoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("destinos")
public class DestinoController {
    @Autowired
    private DestinoRepository destinoRepository;
    @Autowired
    private DestinoService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarDestino(@RequestBody @Valid CadastrarDestinoDTO dados, UriComponentsBuilder uriBuilder) {
        var destino = new Destino(dados);
        destinoRepository.save(destino);
        var uri = uriBuilder.path("destinos/{id}").buildAndExpand(destino.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalharDestinos(destino));
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<DetalharDestinos>> detalharDestino(@PageableDefault(size =10,sort = {"nome"}) Pageable pagina){
         var page = destinoRepository.findAllByAtivoTrue(pagina).map(DetalharDestinos::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody AtualizarDestino dados){
         var destino = destinoRepository.getReferenceById(dados.id());
         destino.atualizar(dados);
         return ResponseEntity.ok(new DadosAtualizados(dados));
    }
    @DeleteMapping("excluir/{id}")//Excluir do banco de dados
    @Transactional
    public ResponseEntity Excluir(@PathVariable Long id){
        var destino = destinoRepository.getReferenceById(id);

        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("desativar/{id}")//Desativar apenas
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id){
      var destino = destinoRepository.getReferenceById(id);
      destino.desativar();

     return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity DetalharByID(@PathVariable Long id){
        var destino = destinoRepository.findById(id);
        if(destino.isPresent()) {
            var dest = destino.get();
           var descritivo = service.descritivoAi(dest);
            return ResponseEntity.ok(new DetalharDestino(dest, descritivo));
        }
        throw new ValidacaoException("nenhum destino foi encontrado.");
    }

}
