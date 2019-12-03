package br.edu.ifrn.persistencia.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ifrn.model.User;
import br.edu.ifrn.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

	 @Autowired
	 private TestEntityManager entityManager;
	
	 @Autowired
	 private UserRepository userRepository;
	
	
	 private String username1 = "BolsonaroMito";
	 private String password1 = "171717";
	 private String name1     = "Jair";  
	 private String lastName1 = "Bolsonaro"; 
	 private String email1     = "jair@presidente.gov.br";
	 
	 private String username2 = "LulaLivre";
	 private String password2 = "131313";
	 private String name2     = "Luis Inacio";  
	 private String lastName2 = "da Silva"; 
	 private String email2    = "lula@pf.gov.br";  
	
	 @Test
	 public void retornaTodosPesistidos() throws IOException {
		 
		 // dado
		 entityManager.persist(new User(username1, password1, name1, lastName1, email1));
		 entityManager.persist(new User(username2, password2, name2, lastName2, email2));
		 
		 // faça
		 Iterable<User> users = userRepository.findAll();
		 User user1 = userRepository.findByUsername(username1).get();
		 User user2 = userRepository.findByUsername(username2).get();
		 
		 // então
		 assertThat(users).hasSize(5);
		 assertThat(users).contains(user1);
		 assertThat(users).contains(user2);
	
		 assertThat(user1.getUsername()).isEqualTo(username1);
		 assertThat(user1.getPassword()).isEqualTo(password1);
		 assertThat(user1.getName()).isEqualTo(name1);
		 assertThat(user1.getLastName()).isEqualTo(lastName1);
		 assertThat(user1.getEmail()).isEqualTo(email1);
	
		 assertThat(user2.getUsername()).isEqualTo(username2);
		 assertThat(user2.getPassword()).isEqualTo(password2);
		 assertThat(user2.getName()).isEqualTo(name2);
		 assertThat(user2.getLastName()).isEqualTo(lastName2);
		 assertThat(user2.getEmail()).isEqualTo(email2);
	  }
	
	  @Test
	  public void criaUsuario() {
		// dado 
		// faça   
		userRepository.save(new User(username1, password1, name1, lastName1, email1));
		User user1 = userRepository.findByUsername(username1).get();
		// então
	    assertThat(user1.getUsername()).isEqualTo(username1);
	    assertThat(user1.getPassword()).isEqualTo(password1);
	  }

}
