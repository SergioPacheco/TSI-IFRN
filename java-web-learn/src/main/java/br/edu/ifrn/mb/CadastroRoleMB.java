package br.edu.ifrn.mb;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrn.model.Role;
import br.edu.ifrn.service.RoleService;

@Named
@ViewScoped
public class CadastroRoleMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Role role = new Role();
	
	private Long idRole;
	
	@Inject
	private RoleService roleService;
	
	
	public void inicializar() {
		if (idRole != null) {
			role = roleService.porId(idRole);
		}
	}
	
	
	public String salvar() {
		roleService.salvar(role);
		return "lista-role.xhtml?faces-redirect=true";
	}
	
	public String excluir() {
		roleService.excluir(role);
		return "lista-role.xhtml?faces-redirect=true";
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public Long getIdRole() {
		return idRole;
	}


	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

}
