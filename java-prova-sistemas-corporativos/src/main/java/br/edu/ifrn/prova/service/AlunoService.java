package br.edu.ifrn.prova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.prova.model.Aluno;
import br.edu.ifrn.prova.repository.AlunoRepository;

@Service  
public class AlunoService {
	
	@Autowired
	private AlunoRepository repository;  
	
	public List<Aluno> findAll() {
		return repository.findAll(); 
	}
		
	public Aluno findOne(Long id) {
		return repository.getOne(id);
	}
	
	public Aluno save(Aluno aluno) {
		return repository.saveAndFlush(aluno);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Aluno> findByEscola(Long escolaId) {
		return repository.findByEscolaId(escolaId);
	}
	
}
