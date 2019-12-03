package br.edu.ifrn.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrn.model.Role;
import br.edu.ifrn.service.RoleService;
import br.edu.ifrn.util.FacesUtil;

@Named
@ViewScoped
public class ListaRoleMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private RoleService roleService;
	
	private List<Role> roles = new ArrayList<>();
	
	private List<Role> roleSelecionadas = new ArrayList<>();
	
	
	@PostConstruct
	public void inicializar() {
		roles = roleService.listAll();
	}
	
	public void excluirSelecionados() {
		for (Role role : roleSelecionadas) {
			roleService.excluir(role);
			roles.remove(role);
		}
		
		FacesUtil.addInfoMessage("Role(s) excluidas(s) com sucesso!");
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getRoleSelecionadas() {
		return roleSelecionadas;
	}

	public void setRoleSelecionadas(List<Role> roleSelecionadas) {
		this.roleSelecionadas = roleSelecionadas;
	}	

}
