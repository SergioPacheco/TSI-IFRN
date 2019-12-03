package br.edu.ifrn.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifrn.dao.UserDAO;
import br.edu.ifrn.model.User;
import br.edu.ifrn.util.Transacional;

public class UserService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserDAO userDAO;	
	
	
	@Transacional
	public void salvar(User user) {
		userDAO.salvar(user);
	}
	
	@Transacional
	public void excluir(User user) {
		userDAO.excluir(user);
	}
	
	public List<User> listAll() {
		return userDAO.listAll();
	}
	
	public User porId(Long id) {
		return userDAO.porId(id);
	}

}
