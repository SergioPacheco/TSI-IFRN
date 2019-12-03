package br.edu.ifrn.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrn.exception.NegocioException;
import br.edu.ifrn.model.Categoria;
import br.edu.ifrn.model.Questao;

public class CategoriaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Categoria salvar(Categoria categoria) {
		return manager.merge(categoria);
	}

	public void excluir(Categoria categoria) {
		try {
			categoria = porId(categoria.getId());
			manager.remove(categoria);
			manager.flush();

		} catch (Exception e) {
			throw new NegocioException("Categoria não pode ser excluida");
		}
	}

	public Categoria porId(Long id) {
		return manager.find(Categoria.class, id);
	}

	public List<Categoria> listAll() {
		return manager.createQuery("from Categoria", Categoria.class).getResultList();
	}

}
