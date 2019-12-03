package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
@Entity
@Table(name="coments")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
 
    @ManyToOne
    @JoinColumn(name="cod_post")
    private Post post;
 
    private String owner;
    private String text;
 
    public Comment() {
    }
 
    public Comment(String owner, String text) {
        this.owner = owner;
        this.text = text;
    }
 
    public Post getPost() {
        return post;
    }
 
    public void setPost(Post post) {
        this.post = post;
    }

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
    
    
}