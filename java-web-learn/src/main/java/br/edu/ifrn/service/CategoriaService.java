package br.edu.ifrn.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifrn.dao.CategoriaDAO;
import br.edu.ifrn.model.Categoria;
import br.edu.ifrn.util.Transacional;

public class CategoriaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaDAO categoriaDAO;	
	
	@Transacional
	public void salvar(Categoria categoria) {
		categoriaDAO.salvar(categoria);
	}
	
	@Transacional
	public void excluir(Categoria categoria) {
		categoriaDAO.excluir(categoria);
	}
	
	public List<Categoria> listAll() {
		return categoriaDAO.listAll();
	}
	
	public Categoria porId(Long id) {
		return categoriaDAO.porId(id);
	}

}
