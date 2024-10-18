package com.Wesleywesp.JornadaMilhas.repository;

import com.Wesleywesp.JornadaMilhas.domain.Depoimento;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;


@Repository
public interface DepoimentoReporitory extends JpaRepository<Depoimento, Long>{
    @Query("SELECT d FROM Depoimento d WHERE d.ativo = true ORDER BY RAND()")
    Page<Depoimento> findAllByAtivoTrueOrderByRandom(Pageable pageable);

    Page<Depoimento> findAllByAtivoTrue(Pageable pagina);
}
