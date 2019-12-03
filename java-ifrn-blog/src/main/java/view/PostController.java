package view;

import model.Post;
import dao.PostRepository;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "postController")
@ApplicationScoped
public class PostController {

    private PostRepository post = new PostRepository();

    public List<Post> getAllPosts() {
        return post.getAll();
    }

    public Post getPostByTitle() {
        String title = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("title");

        return post.getPostByTitle(title);
    }
}