package br.edu.ifrn.persistencia.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ifrn.model.Post;
import br.edu.ifrn.model.User;
import br.edu.ifrn.repository.PostRepository;
import br.edu.ifrn.repository.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryIntegrationTest {

	 @Autowired
	 private TestEntityManager entityManager;
	
	 @Autowired
	 private PostRepository postRepository;
	 
	 @Autowired
	 private UserRepository userRepository;
	 
	 private String username1 = "BolsonaroMito";
	 private String password1 = "171717";
	 private String name1     = "Jair";  
	 private String lastName1 = "Bolsonaro"; 
	 private String email1    = "jair@presidente.gov.br";
	
	 private String title1    = "Bolsonaro é Eleito Presidente do Brasil";
	 private String body1     = "Capitão da reserva, deputado é o primeiro político saído do Exército a assumir o comando do país desde o fim do regime militar";
	 private Timestamp createDate1 = new Timestamp(System.currentTimeMillis());
	
	 private String title2 = "Lula é declarado preso político";
	 private String body2  = "FIDH, com sede em Madri, destaca que ex-presidente é condenado sem provas genuínas, teve processo violado e parte dos juízes em manifesta parcialidade contra si";
	 private Timestamp createDate2 = new Timestamp(System.currentTimeMillis());
	 
	 User user1; 
	 
	 @Before
     public void setup() {
		// dado
		entityManager.persist(new User(username1, password1, name1, lastName1, email1));
		user1 = userRepository.findByUsername(username1).get();
     }
	 
	 @Test
	 public void retornaTodosPesistidos() throws IOException {
		 
		 // dado	 
		 entityManager.persist(new Post(title1, body1, createDate1, user1));
		 entityManager.persist(new Post(title2, body2, createDate2, user1));
		 
		 // faca
		 Iterable<Post> posts = postRepository.findAll();
		 Post post1 = postRepository.findByTitle(title1).get();
		 Post post2 = postRepository.findByTitle(title2).get();
		 
		 // então
		 assertThat(posts).hasSize(14);
		 assertThat(posts).contains(post1);
		 assertThat(posts).contains(post2);
	
		 assertThat(post1.getTitle()).isEqualTo(title1);
		 assertThat(post1.getBody()).isEqualTo(body1);
	
		 assertThat(post2.getTitle()).isEqualTo(title2);
		 assertThat(post2.getBody()).isEqualTo(body2);
	  }
	
	  @Test
	  public void criaPost() {
		// dado
		// faça   
				  
		postRepository.save(new Post(title1, body1, createDate2, user1));
		Post post1 = postRepository.findByTitle(title1).get();
		// então
	    assertThat(post1.getTitle()).isEqualTo(title1);
	    assertThat(post1.getBody()).isEqualTo(body1);
	  }

}
