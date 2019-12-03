package br.edu.ifrn.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrn.exception.NegocioException;
import br.edu.ifrn.model.Categoria;
import br.edu.ifrn.model.Questao;
import br.edu.ifrn.model.User;

public class UserDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public User salvar(User user) {
		return manager.merge(user);
	}

	public void excluir(User user) {
		try {
			user = porId(user.getId());
			manager.remove(user);
			manager.flush();

		} catch (Exception e) {
			throw new NegocioException("Usuario não pode ser excluido");
		}
	}

	public User porId(Long id) {
		return manager.find(User.class, id);
	}

	public List<User> listAll() {
		return manager.createQuery("from User", User.class).getResultList();
	}
	

	public User getUser(String password, String username) {
		
		TypedQuery<User> u = manager.createQuery("from user s where" + " s.username='" 
		+ username + "' AND s.password='" + password + "'", User.class);

		return u.getSingleResult();
	

	}

}
