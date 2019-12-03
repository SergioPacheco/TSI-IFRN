package br.edu.ifrn.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.ifrn.exception.NegocioException;
import br.edu.ifrn.model.Resposta;

public class RespostaDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Resposta salvar(Resposta resposta) {
		return manager.merge(resposta);
	}
	
	public void excluir(Resposta resposta) {
		try {
			resposta = porId(resposta.getId());
			manager.remove(resposta);
			manager.flush();
			
		} catch (Exception e) {			
			throw new NegocioException("Resposta não pode ser excluida");
		}
	}

	public Resposta porId(Long id) {		
		return manager.find(Resposta.class, id);
	}
	
	public List<Resposta> listAll() {
		return manager.createNativeQuery("SELECT * FROM Tarefa", Resposta.class).getResultList();
	}

}
