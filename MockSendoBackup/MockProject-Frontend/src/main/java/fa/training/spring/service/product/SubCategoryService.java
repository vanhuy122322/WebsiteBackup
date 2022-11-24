package fa.training.spring.service.product;

import org.springframework.ui.Model;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.product.SubCategoryDTO;

public interface SubCategoryService {
    void getAll(Model model);

    void add(RequestDTO<SubCategoryDTO> requestDTO);

    void updatePage(String id, Model model);

    void update(RequestDTO<SubCategoryDTO> requestDTO);

    void delete(String id);
}
