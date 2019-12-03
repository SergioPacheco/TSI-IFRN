package br.edu.ifrn.localizaimovel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.localizaimovel.domain.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer> {

	List<Imovel> findByTipoStartingWithIgnoreCase(String text);
	
	List<Imovel> findByPrecoBetween(Double valorInicial, Double valorFinal);

	List<Imovel> findByModalidadeVendaStartingWithIgnoreCase(String text);
	
	List<Imovel> findByDescricaoContainingIgnoreCase(String text);
	
}
