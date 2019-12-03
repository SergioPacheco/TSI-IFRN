package view;
 
import model.Category;
import dao.CategoryRepository;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "categoryController")
@ApplicationScoped
public class CategoryController {

    private CategoryRepository cat = new CategoryRepository();

    public List<Category> getTags() {
        return cat.getAll();
    }
}