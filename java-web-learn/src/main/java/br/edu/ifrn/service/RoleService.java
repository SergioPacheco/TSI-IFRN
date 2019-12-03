package br.edu.ifrn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifrn.dao.RoleDAO;
import br.edu.ifrn.model.Role;
import br.edu.ifrn.util.Transacional;

public class RoleService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RoleDAO roleDAO;	
	
	
	@Transacional
	public void salvar(Role role) {
		roleDAO.salvar(role);
	}
	
	@Transacional
	public void excluir(Role role) {
		roleDAO.excluir(role);
	}
	
	
	public List<Role> listAll() {
		return roleDAO.listAll();
	}
	
	public Role porId(Long id) {
		return roleDAO.porId(id);
	}

}
