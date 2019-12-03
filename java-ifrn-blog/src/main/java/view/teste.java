package view;

import dao.*; 
import model.*;
import javax.persistence.EntityManager;

public class teste {
 
    public static void main(String[] args) {
 
         
    	Category cat = new Category(); 
    	Category cat2 = new Category();
    	cat.setName("Hibernate");
    	cat2.setName("JPA");
        Post post = new Post("Titulo do Post", "Esse é o texto do primeiro post");
        Comment coment = new Comment("Givanaldo", "primeiro comentario do professor Giva ... ");
        Comment coment2 = new Comment("Sergio", "segundo comentario do aluno Sergio ... ");
        
        post.addComent(coment);
        post.addComent(coment2);
        post.addCategory(cat);
        post.addCategory(cat2);
        
 
        EntityManager emf = JpaUtil.getEntityManager();
        
 
        PostRepository repo = new PostRepository(emf);
 
        repo.addPost(post);
 
        for(Post p : repo.getAll()){
            System.out.println("Title  : " + p.getTitle());
            System.out.println("Text   : " + p.getText());
            
            System.out.println("----");	
            for(Category ca : p.getCategories()){
                System.out.println("Name : " + ca.getName());
            }
            System.out.println("----");	
            
            for(Comment c : p.getComments()){
                System.out.println("Owner  : " + c.getOwner());
                System.out.println("Comment: " + c.getText());
            }
            System.out.println("------------------------------------");	
        } 
    }
}