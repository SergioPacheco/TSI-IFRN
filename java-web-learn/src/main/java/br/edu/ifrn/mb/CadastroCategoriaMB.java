package br.edu.ifrn.mb;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrn.model.Categoria;
import br.edu.ifrn.service.CategoriaService;

@Named
@ViewScoped
public class CadastroCategoriaMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Categoria categoria = new Categoria();
	
	private Long idCategoria;
	
	@Inject
	private CategoriaService categoriaService;
	
	
	public void inicializar() {
		if (idCategoria != null) {
			categoria = categoriaService.porId(idCategoria);
		}
	}
	
	
	public String salvar() {
		categoriaService.salvar(categoria);
		return "lista-categoria.xhtml?faces-redirect=true";
	}
	
	public String excluir() {
		categoriaService.excluir(categoria);
		return "lista-categoria.xhtml?faces-redirect=true";
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Long getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

}
