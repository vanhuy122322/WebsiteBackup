package fa.training.spring.service.product;

import org.springframework.ui.Model;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.product.CategoryDTO;

/**
 * CategoryService
 */
public interface CategoryService {
    void getAll(Model model);

    void getAllCategoryByStatus(Model model);

    void add(RequestDTO<CategoryDTO> requestDTO);

    void updatePage(String id, Model model);

    void update(RequestDTO<CategoryDTO> requestDTO);

    void deleteCategory(String id);

    void deleteSubCategory(String id,String slug);

    CategoryDTO search(RequestDTO<CategoryDTO> requestDTO);

}