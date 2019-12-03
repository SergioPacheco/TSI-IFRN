package br.edu.ifrn.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrn.model.Categoria;
import br.edu.ifrn.service.CategoriaService;
import br.edu.ifrn.util.FacesUtil;

@Named
@ViewScoped
public class ListaCategoriaMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaService categoriaService;
	
	private List<Categoria> categorias = new ArrayList<>();
	
	private List<Categoria> categoriaSelecionadas = new ArrayList<>();
	
	
	@PostConstruct
	public void inicializar() {
		categorias = categoriaService.listAll();
	}
	
	public void excluirSelecionados() {
		for (Categoria categoria : categoriaSelecionadas) {
			categoriaService.excluir(categoria);
			categorias.remove(categoria);
		}
		
		FacesUtil.addInfoMessage("Categorias excluidas) com sucesso!");
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Categoria> getCategoriaSelecionadas() {
		return categoriaSelecionadas;
	}

	public void setCategoriaSelecionadas(List<Categoria> categoriaSelecionadas) {
		this.categoriaSelecionadas = categoriaSelecionadas;
	}	

}
