package fa.training.spring.controller.client.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.product.ProductDTO;
import fa.training.spring.service.product.ProductService;

@Controller
public class ProductClientController {

	@Autowired
	ProductService productService;

	@GetMapping("/product-detail/{slug}")
	public String ProductDetailPage(@PathVariable String slug, Model model) {
		productService.getOneBySlug(slug, model);
		return "pages/client/product/ProductDetail";
	}

	@GetMapping("/category/{Category}")
	public String getProducCategoryNamePage(@PathVariable(required = true) String Category, Model model) {
		productService.getProductByCategoryName(Category, model);
		return "pages/client/product/ProductCategory";
	}

	@GetMapping("/Subcategory/{subCategory}")
	public String getProductSubCategoryPage(@PathVariable("subCategory") String subCategory, Model model) {
		productService.getProductBySubCategoryName(subCategory, model);
		return "pages/client/product/ProductSubCategory";
	}

	@PostMapping("/search-by-product-detail")
	@ResponseBody
	public ResponseDTO<ProductDTO> getProductByProuctDetail(RequestDTO<ProductDTO> requestDTO) {
		return productService.getProductByProductDetail(requestDTO);
	}


	@PostMapping("/search-product")
	@ResponseBody
	public List<ProductDTO> searchProduct(ProductDTO productDTO) {
		RequestDTO<ProductDTO> requestDTO = new RequestDTO<ProductDTO>(productDTO);
		return productService.search(requestDTO);
	}
}
