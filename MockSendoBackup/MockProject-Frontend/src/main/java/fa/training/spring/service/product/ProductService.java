package fa.training.spring.service.product;

import java.util.List;

import org.springframework.ui.Model;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.product.ProductDTO;

/**
 * ProductService
 */
public interface ProductService {
    void getAll(Model model);

    void add(RequestDTO<ProductDTO> requestDTO);

    void updatePage(String id, Model model);

    void update(RequestDTO<ProductDTO> requestDTO);

    void delete(String id);

    void getOneBySlug(String slug, Model model);

    public void getProductByCategoryName(String name, Model model);

    public void getProductBySubCategoryName(String subcategory, Model model);

    public ResponseDTO<ProductDTO> getProductByProductDetail(RequestDTO<ProductDTO> requestDTO);

    List<ProductDTO> search(RequestDTO<ProductDTO> requestDTO);
    
    void sendDataFromExcelToAPIAndSave(RequestDTO<ProductDTO> requestDTO);
}