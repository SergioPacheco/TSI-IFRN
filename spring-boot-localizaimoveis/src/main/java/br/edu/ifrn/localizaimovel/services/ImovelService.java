package br.edu.ifrn.localizaimovel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.edu.ifrn.localizaimovel.domain.Imovel;
import br.edu.ifrn.localizaimovel.repository.ImovelRepository;
import br.edu.ifrn.localizaimovel.services.exception.ObjectNotFoundException;

@Service
public class ImovelService {
	
	@Autowired
	private ImovelRepository repo;
	
	public List<Imovel> findAll() {
		return repo.findAll();
	}
	
	public Imovel findById(Integer id) {
		Optional<Imovel> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Imovel não encontrado"));
	}
	
	public List<Imovel> findByTipo(String text) {
		return repo.findByTipoStartingWithIgnoreCase(text);
	}
	public Page<Imovel> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
}	

	public List<Imovel> buscaPorPreco(Double valorInicial, Double valorFinal) {
		return repo.findByPrecoBetween(valorInicial, valorFinal);
	}
	
	public Imovel insert(Imovel obj) {
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public Imovel update(Imovel obj) {
		Imovel newObj = findById(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	
	private void updateData(Imovel newObj, Imovel obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setFoto(obj.getFoto());
		newObj.setLink(obj.getLink());
		newObj.setPreco(obj.getPreco());
	}


}
