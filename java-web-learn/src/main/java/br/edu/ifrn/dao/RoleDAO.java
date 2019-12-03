package br.edu.ifrn.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.ifrn.exception.NegocioException;
import br.edu.ifrn.model.Categoria;
import br.edu.ifrn.model.Role;

public class RoleDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Role salvar(Role role) {
		return manager.merge(role);
	}
	
	public void excluir(Role role) {
		try {
			role = porId(role.getId());
			manager.remove(role);
			manager.flush();
			
		} catch (Exception e) {			
			throw new NegocioException("Role não pode ser excluida");
		}
	}

	public Role porId(Long id) {		
		return manager.find(Role.class, id);
	}
	
	public List<Role> listAll() {
		return manager.createQuery("from Role", Role.class).getResultList();
	}

}
