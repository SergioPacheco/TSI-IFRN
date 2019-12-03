package br.edu.ifrn.prova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.prova.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	public List<Aluno> findByEscolaId(Long escolaId);

}
