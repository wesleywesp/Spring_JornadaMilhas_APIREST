package com.Wesleywesp.JornadaMilhas.repository;

import com.Wesleywesp.JornadaMilhas.domain.Destino;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long> {

    Page<Destino>findAllByAtivoTrue(Pageable pagina);

    Destino getReferenceByNome(String nome);
}
