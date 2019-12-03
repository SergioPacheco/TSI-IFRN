package dao;

import model.Category;
 
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
 
public class CategoryRepository {
 
    EntityManager em;
 
    public CategoryRepository(){
    }
 
    public CategoryRepository(EntityManager em){
        this.em = em;
    }
 
    public List<Category> getAll() {
        TypedQuery<Category> query = em.createQuery("from Category", Category.class);
		return query.getResultList();
    }
    
    public Category getCategoryByName(String name) {
        Query query = em.createQuery("Select p from Category p where p.name = :name");
        query.setParameter("name", name);
        return (Category) query.getResultList().get(0);
    }
 
    public void addPost(Category category) {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }
 
    public void updatePost(Category category) {
        em.getTransaction().begin();
        em.merge(category);
        em.flush();
        em.getTransaction().commit();
    }
}