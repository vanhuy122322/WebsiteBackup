package fa.training.spring.controller.productcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.productdto.CategoryDTO;
import fa.training.spring.dto.productdto.ProductDTO;
import fa.training.spring.dto.shopdto.ShopDTO;
import fa.training.spring.service.productservice.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping
	public ResponseEntity<ResponseDTO<ProductDTO>> getAll() {
		return ResponseEntity.ok(new ResponseDTO<ProductDTO>(productService.getAll(), HttpStatus.OK));
	}

	@PostMapping
	public ResponseEntity<ResponseDTO<ProductDTO>> getByID(@RequestBody RequestDTO<ProductDTO> requestDTO) {
		return ResponseEntity.ok(new ResponseDTO<ProductDTO>(productService.getOneById(requestDTO), HttpStatus.OK));
	}

	@PostMapping("/add")
	public ResponseEntity<ResponseDTO<ProductDTO>> add(@RequestBody RequestDTO<ProductDTO> requestDTO) {
		return ResponseEntity
				.ok(new ResponseDTO<ProductDTO>(productService.add(requestDTO), "Create Successfully",
						HttpStatus.CREATED));
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseDTO<ProductDTO>> update(@RequestBody RequestDTO<ProductDTO> requestDTO) {
		return ResponseEntity
				.ok(new ResponseDTO<ProductDTO>(productService.update(requestDTO), "Update Successfully",
						HttpStatus.CREATED));

	}

	@PostMapping("/delete")
	public ResponseEntity<ResponseDTO<ProductDTO>> delete(@RequestBody RequestDTO<ProductDTO> requestDTO) {
		boolean isDeleted = productService.delete(requestDTO);
		if (isDeleted) {
			return ResponseEntity
					.ok(new ResponseDTO<ProductDTO>(requestDTO.getData(), "Delete Successfully", HttpStatus.OK));
		} else {
			return ResponseEntity
					.ok(new ResponseDTO<ProductDTO>(new ProductDTO(), "Delete Fail", HttpStatus.NO_CONTENT));
		}

	}

	@PostMapping("/findBySlug")
	public ResponseEntity<ResponseDTO<ProductDTO>> getBySlug(@RequestBody RequestDTO<ProductDTO> requestDTO) {
		return ResponseEntity.ok(new ResponseDTO<ProductDTO>(productService.getOneBySlug(requestDTO), HttpStatus.OK));
	}

	@PostMapping(value = "/search")
	public ResponseEntity<ResponseDTO<ProductDTO>> getMethodName(@RequestBody RequestDTO<ProductDTO> requestDTO) {
		return ResponseEntity.ok(new ResponseDTO<ProductDTO>(productService.search(requestDTO), HttpStatus.OK));
	}

	@PostMapping("/findByCategoryOrSubCategory")
	public ResponseEntity<ResponseDTO<ProductDTO>> findProductByCategoryOrSubCategory(
			@RequestBody RequestDTO<ProductDTO> requestDTO) {
	    System.out.println(requestDTO);
		return ResponseEntity
				.ok(new ResponseDTO<ProductDTO>(productService.getProductBySubCategory(requestDTO), HttpStatus.OK));
	}

	@PostMapping("/findByProductDetail")
	public ResponseEntity<ResponseDTO<ProductDTO>> findByProductDetail(@RequestBody RequestDTO<ProductDTO> requestDTO) {
		return ResponseEntity
				.ok(new ResponseDTO<ProductDTO>(productService.getProductByProductDetail(requestDTO), HttpStatus.OK));
	}

	
	  @PostMapping("/findProductByShopName")
	    public ResponseEntity<ResponseDTO<ProductDTO>> getProductByShopName(@RequestBody RequestDTO<ShopDTO> requestDTO) {
	        return ResponseEntity.ok(new ResponseDTO<ProductDTO>(productService.getProductByShopName(requestDTO), HttpStatus.OK));
	    }
	  
	  
	  @PostMapping("/add-list")
	    public ResponseEntity<ResponseDTO<ProductDTO>> addList(@RequestBody RequestDTO<ProductDTO> requestDTO) {    
	        try {
	            if (productService.addList(requestDTO)) {
	                return ResponseEntity
	                        .ok(new ResponseDTO<ProductDTO>(requestDTO.getListData(), "Trainee Create Successfully", HttpStatus.CREATED));
	            } else {
	                return ResponseEntity.ok(
	                        new ResponseDTO<ProductDTO>(requestDTO.getListData(), "Trainee Delete Fail", HttpStatus.NO_CONTENT));
	            }

	        } catch (Exception e) {
	            return ResponseEntity.ok(
	                    new ResponseDTO<ProductDTO>(new ProductDTO(), "Error Server", HttpStatus.INTERNAL_SERVER_ERROR));
	        }
	    }
}
