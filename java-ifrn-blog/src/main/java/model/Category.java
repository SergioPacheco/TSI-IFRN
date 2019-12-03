package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
 
@Entity
@Table(name="categories")
public class Category implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
 
    private String name;
    
	@ManyToMany (mappedBy = "categories", cascade = CascadeType.PERSIST)
    private Set<Post> posts = new HashSet<>();
 
    public  Set<Post> getPosts() {
        return posts;
    }
 
    public void addPost(Post post){
        posts.add(post);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
 
     
}
