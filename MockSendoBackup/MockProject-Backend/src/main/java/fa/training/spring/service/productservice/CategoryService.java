package fa.training.spring.service.productservice;

import java.util.List;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.CategoryDTO;
import fa.training.spring.dto.productdto.ProductDTO;
import fa.training.spring.exception.NotFoundException;

public interface CategoryService {

    boolean add(RequestDTO<CategoryDTO> req);

    CategoryDTO getOneById(RequestDTO<CategoryDTO> req);

    List<CategoryDTO> getAll();

    List<CategoryDTO> getAllCategoryStatusFalse();

    CategoryDTO update(RequestDTO<CategoryDTO> req) throws NotFoundException;

    boolean deleteCategory(RequestDTO<CategoryDTO> req);

    CategoryDTO deleteSubCategory(RequestDTO<CategoryDTO> req);

    CategoryDTO search(RequestDTO<CategoryDTO> requestDTO);

    CategoryDTO getCategoryByCategory(RequestDTO<ProductDTO> requestDTO);

    CategoryDTO getCategoryBySubCategory(RequestDTO<ProductDTO> requestDTO);

}
