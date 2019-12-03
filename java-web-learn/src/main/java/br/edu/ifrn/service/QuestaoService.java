package br.edu.ifrn.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifrn.dao.QuestaoDAO;
import br.edu.ifrn.model.Questao;
import br.edu.ifrn.model.Resposta;
import br.edu.ifrn.util.Transacional;

public class QuestaoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private QuestaoDAO questaoDAO;	
	
	@Inject
	
	
	@Transacional
	public void salvar(Questao questao) {
		questaoDAO.salvar(questao);
	}
	
	@Transacional
	public void excluir(Questao questao) {
		questaoDAO.excluir(questao);
	}
	
	
	public List<Questao> listAll() {
		return questaoDAO.listAll();
	}
	
	public Questao porId(Long id) {
		return questaoDAO.porId(id);
	}
	
	

}
