package br.edu.ifrn.prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.prova.model.Escola;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Long> {

}
