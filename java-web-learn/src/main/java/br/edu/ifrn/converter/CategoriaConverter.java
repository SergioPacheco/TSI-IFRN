/*
package br.edu.ifrn.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifrn.dao.CategoriaDAO;
import br.edu.ifrn.model.Categoria;
import br.edu.ifrn.cdi.CDIServiceLocator;
public class CategoriaConverter {

}

@FacesConverter(forClass=Categoria.class)
public class CategoriaConverter implements Converter {

	private CategoriaDAO categoriaDAO;
	
	public CategoriaConverter() {
		this.fabricanteDAO = CDIServiceLocator.getBean(CategoriaDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;

		if (value != null) {
			retorno = this.categoriaDAO.buscarPorId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Categoria) value).getCodigo();
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}

}
*/