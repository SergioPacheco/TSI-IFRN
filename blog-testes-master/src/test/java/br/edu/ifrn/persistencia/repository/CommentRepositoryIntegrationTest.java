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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.edu.ifrn.model.Comment;
import br.edu.ifrn.model.Post;
import br.edu.ifrn.model.User;
import br.edu.ifrn.repository.CommentRepository;
import br.edu.ifrn.repository.PostRepository;
import br.edu.ifrn.repository.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryIntegrationTest {

	 @Autowired
	 private TestEntityManager entityManager;
	
	 @Autowired
	 private PostRepository postRepository;
	 
	 @Autowired
	 private CommentRepository commentRepository;
	 
	 @Autowired
	 private UserRepository userRepository;
	 
	 private String username1 = "BolsonaroMito";
	 private String password1 = "171717";
	 private String name1     = "Jair";  
	 private String lastName1 = "Bolsonaro"; 
	 private String email1    = "jair@presidente.gov.br";
	
	 private String title1 = "Bolsonaro é Eleito Presidente do Brasil";
	 private String body1  = "Capitão da reserva, deputado é o primeiro político saído do Exército a assumir o comando do país desde o fim do regime militar";
	 private Timestamp createDate1 = new Timestamp(System.currentTimeMillis());

	 private String cBody1 = "Desenvolver software é plantar sonhos e colher pesadelos"; 
	 private String cBody2 = "A vida é muito curta para ser levada a sério"; 
	 private Timestamp createDate2 = new Timestamp(System.currentTimeMillis());
	 
	 User user1; 
	 Post post1; 
	 
	 @Before
     public void setup() {
		// dado
		entityManager.persist(new User(username1, password1, name1, lastName1, email1));
		user1 = userRepository.findByUsername(username1).get();
		 
		entityManager.persist(new Post(title1, body1, createDate1, user1));
		post1 = postRepository.findByTitle(title1).get();
     }
	 	 
	 @Test
	 public void retornaTodosPesistidos() throws IOException {
		 
		 entityManager.persist(new Comment(cBody1, createDate2, post1, user1));
		 entityManager.persist(new Comment(cBody2, createDate2, post1, user1));
		 		 
		 // faça 
		 Iterable<Comment> comments = commentRepository.findAll();
		 Comment comment1 = commentRepository.findByBody(cBody1).get();
		 Comment comment2 = commentRepository.findByBody(cBody2).get();
		 
		 // então
		 assertThat(comments).hasSize(8);
		 assertThat(comments).contains(comment1);
		 assertThat(comments).contains(comment2);
	
		 assertThat(comment1.getBody()).isEqualTo(cBody1);
		 assertThat(comment1.getPost()).isEqualTo(post1);
		 assertThat(comment1.getUser()).isEqualTo(user1);
	
		 assertThat(comment2.getBody()).isEqualTo(cBody2);
		 assertThat(comment2.getPost()).isEqualTo(post1);
		 assertThat(comment2.getUser()).isEqualTo(user1);
		 
	  }
	
	  @Test
	  public void criaComentario() {
		// dado
		// faça   
		entityManager.persist(new Comment(cBody1, createDate2, post1, user1));
		Comment comment1 = commentRepository.findByBody(cBody1).get();
		 
		// então
		assertThat(comment1.getBody()).isEqualTo(cBody1);
	  }

}
