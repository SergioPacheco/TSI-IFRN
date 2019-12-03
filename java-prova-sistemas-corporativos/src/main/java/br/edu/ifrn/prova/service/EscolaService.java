package br.edu.ifrn.prova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.prova.model.Escola;
import br.edu.ifrn.prova.repository.EscolaRepository;

@Service  
public class EscolaService {
	
	@Autowired
	private EscolaRepository repository;  
	
	public List<Escola> findAll() {
		return repository.findAll(); 
	}
	
	public Escola findOne(Long id) {
		return repository.getOne(id);
	}
	
	public Escola save(Escola escola) {
		return repository.saveAndFlush(escola);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
