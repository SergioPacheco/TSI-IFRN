package br.edu.ifrn.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.edu.ifrn.model.Usuario;
import br.edu.ifrn.repositories.custom.IRepositorySenhaCustom;

@RepositoryRestResource(collectionResourceRel = "usuario", path = "usuario")
public interface IRepositoryUsuario extends CrudRepository<Usuario, Long>, IRepositorySenhaCustom {
	
	@Query("SELECT T1 FROM Usuario T1 WHERE T1.login = ?1 AND T1.senha = ?2")
	Usuario validarUsuario(String login, String senha);
	
}
