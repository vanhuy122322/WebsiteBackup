package fa.training.spring.controller.client.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fa.training.spring.service.product.CategoryService;

@Controller
public class ProductCategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public String getAllCategory(Model model) {
        categoryService.getAll(model);
        return "pages/client/product/Category";
    }
}
