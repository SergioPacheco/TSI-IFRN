package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
 
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
 
@Entity
@Table(name="posts")
public class Post implements Serializable {
 
private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
 
    @Column(name = "title", nullable = false)
    private String title;
 
    @Column(name = "text", length = 10000) 
    private String text;
 
    @OneToMany(mappedBy = "post", cascade=(ALL), fetch=LAZY)
    private List<Comment> comments = new ArrayList<Comment>();
 
    @ManyToMany
    @JoinTable    (name = "posts_categories", 
    		joinColumns = @JoinColumn(name = "cod_posts"), 
     inverseJoinColumns = @JoinColumn(name = "cod_categories"))
    private Set<Category> categories = new HashSet<>();
 
    public Post() {
    }
 
    public Post(String title, String text) {
        this.title = title;
        this.text = text;
    }
 
    public void addComent(Comment c){
        comments.add(c);
        c.setPost(this);
    }
 
    public List<Comment> getComments() {
        return comments;
    }
 
    public Set<Category> getCategories() {
        return categories;
    }
 
    public void addCategory(Category category){
        categories.add(category);
        category.addPost(this);
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}