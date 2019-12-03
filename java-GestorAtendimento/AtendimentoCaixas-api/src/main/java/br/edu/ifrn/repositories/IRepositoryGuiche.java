package br.edu.ifrn.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.edu.ifrn.model.Guiche;
import br.edu.ifrn.repositories.custom.IRepositorySenhaCustom;

@RepositoryRestResource(collectionResourceRel = "guiche", path = "guiche")
public interface IRepositoryGuiche extends CrudRepository<Guiche, Long>, IRepositorySenhaCustom {
	
}