package fa.training.spring.service.productservice;

import java.util.List;

import org.springframework.stereotype.Service;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.CategoryDTO;
import fa.training.spring.dto.productdto.ProductDTO;
import fa.training.spring.dto.shopdto.ShopDTO;

@Service
public interface ProductService {

    boolean delete(RequestDTO<ProductDTO> requestDTO);

    ProductDTO update(RequestDTO<ProductDTO> requestDTO);

    ProductDTO add(RequestDTO<ProductDTO> requestDTO);

    ProductDTO getOneById(RequestDTO<ProductDTO> requestDTO);

    ProductDTO getOneByName(RequestDTO<ProductDTO> requestDTO);

    List<ProductDTO> getAll();

    ProductDTO getOneBySlug(RequestDTO<ProductDTO> requestDTO);

    List<ProductDTO> search(RequestDTO<ProductDTO> requestDTO);

    List<ProductDTO> getProductBySubCategory(RequestDTO<ProductDTO> requestDTO);

    List<ProductDTO> getProductByProductDetail(RequestDTO<ProductDTO> requestDTO);

    CategoryDTO getSubCategoryBySubCategories(RequestDTO<ProductDTO> requestDTO);

    CategoryDTO getCategoryByCategory(RequestDTO<ProductDTO> requestDTO);

    List<ProductDTO> getProductByShopName(RequestDTO<ShopDTO> req);

    boolean addList(RequestDTO<ProductDTO> requestDTO);

}
