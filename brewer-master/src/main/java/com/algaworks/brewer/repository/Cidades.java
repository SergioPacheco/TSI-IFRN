package com.algaworks.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.model.Estado;
import com.algaworks.brewer.repository.helper.cidade.CidadesQueries;

public interface Cidades extends JpaRepository<Cidade, Long>, CidadesQueries{

	//Consulta lista de cidade por um codigo de estado
	public List<Cidade> findByEstadoCodigo(Long codigoEstado);
	
	//Consulta cidade por um nome e um estado
	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
}
