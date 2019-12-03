package dao;

 
import model.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
 
public class PostRepository {
 
    EntityManager em;
 
    public PostRepository(){
 
    }
 
    public PostRepository(EntityManager em){
        this.em = em;
    }
 
    public List<Post> getAll() {
        TypedQuery<Post> query = em.createQuery("from Post", Post.class);
		return query.getResultList();
    }
    
   
    public Post getPostByTitle(String title) {
        Query query = em.createQuery("Select p from Post p where p.title = :title");
        query.setParameter("title", title);
        return (Post) query.getResultList().get(0);
    }
 
    public void addPost(Post post) {
        em.getTransaction().begin();
        em.persist(post);
        em.getTransaction().commit();
    }
 
    public void updatePost(Post post) {
        em.getTransaction().begin();
        em.merge(post);
        em.flush();
        em.getTransaction().commit();
    }
}