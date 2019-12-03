package br.edu.ifrn.mb;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrn.model.User;
import br.edu.ifrn.service.UserService;

@Named
@ViewScoped
public class CadastroUserMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private User user = new User();
	
	private Long idUser;
	
	@Inject
	private UserService userService;
	
	
	public void inicializar() {
		if (idUser != null) {
			user = userService.porId(idUser);
		}
	}
		
	public String salvar() {
		userService.salvar(user);
		return "lista-user.xhtml?faces-redirect=true";
	}
	
	public String excluir() {
		userService.excluir(user);
		return "lista-user.xhtml?faces-redirect=true";
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Long getIdUser() {
		return idUser;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

}
