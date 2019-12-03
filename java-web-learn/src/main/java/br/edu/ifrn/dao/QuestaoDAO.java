package br.edu.ifrn.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrn.exception.NegocioException;
import br.edu.ifrn.model.Categoria;
import br.edu.ifrn.model.Questao;
import br.edu.ifrn.dao.CategoriaDAO;

public class QuestaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Inject
	private CategoriaDAO categoriaDAO;

	public Questao salvar(Questao questao) {
		return manager.merge(questao);
	}

	public void excluir(Questao questao) {
		try {
			questao = porId(questao.getId());
			manager.remove(questao);
			manager.flush();

		} catch (Exception e) {
			throw new NegocioException("Questao não pode ser excluida");
		}
	}

	public Questao porId(Long id) {
		return manager.find(Questao.class, id);
	}

	// public List<Empresa> pesquisar(String nomeFantasia) {
	// TypedQuery<Empresa> consulta = manager.createQuery(
	// "from Empresa where nomeFantasia like :nomeFantasia",
	// Empresa.class);
	// consulta.setParameter("nomeFantasia", nomeFantasia + "%");
	// return consulta.getResultList();
	// }

	public List<Questao> listAll() {
		TypedQuery<Questao> q = manager.createQuery("from Questao", Questao.class);
		return q.getResultList();
	}

	public List<Questao> getQuestaoPorUsuario(Long id) {
		TypedQuery<Questao> q =  manager.createQuery("from Questao where user_id=" + id, Questao.class);
		return q.getResultList();
	}

	public List<Questao> getQuestaoRandomica(Long id_categ, int m) {
		TypedQuery<Questao> quest = manager
				.createQuery("from Questao where categoria_id=" + id_categ + " order by rand() ", Questao.class)
				.setMaxResults(m);

		return quest.getResultList();
	}
	
	
		

}
