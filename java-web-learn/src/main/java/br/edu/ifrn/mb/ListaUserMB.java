package br.edu.ifrn.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrn.model.User;
import br.edu.ifrn.service.UserService;
import br.edu.ifrn.util.FacesUtil;

@Named
@ViewScoped
public class ListaUserMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserService userService;
	
	private List<User> users = new ArrayList<>();
	
	private List<User> userSelecionadas = new ArrayList<>();
	
	
	@PostConstruct
	public void inicializar() {
		users = userService.listAll();
	}
	
	public void excluirSelecionados() {
		for (User user : userSelecionadas) {
			userService.excluir(user);
			users.remove(user);
		}
		
		FacesUtil.addInfoMessage("User(s) excluída(s) com sucesso!");
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUserSelecionadas() {
		return userSelecionadas;
	}

	public void setUserSelecionadas(List<User> userSelecionadas) {
		this.userSelecionadas = userSelecionadas;
	}	

}
