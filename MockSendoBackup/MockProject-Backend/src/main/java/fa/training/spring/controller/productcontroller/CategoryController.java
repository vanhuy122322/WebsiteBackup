package fa.training.spring.controller.productcontroller;

import java.util.List;

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
import fa.training.spring.exception.NotFoundException;
import fa.training.spring.service.productservice.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping
	public ResponseEntity<ResponseDTO<CategoryDTO>> getAll() {
		try {
			List<CategoryDTO> list = categoryService.getAll();
			if (list.isEmpty()) {
				return ResponseEntity
						.ok(new ResponseDTO<CategoryDTO>(new CategoryDTO(), " Not Found List", HttpStatus.NOT_FOUND));
			} else {
				ResponseDTO<CategoryDTO> responseLitsCategory = new ResponseDTO<CategoryDTO>(list, HttpStatus.OK);
				return ResponseEntity.ok(responseLitsCategory);
			}
		} catch (Exception e) {
			return ResponseEntity.ok(
					new ResponseDTO<CategoryDTO>(new CategoryDTO(), "Error Server", HttpStatus.INTERNAL_SERVER_ERROR));
		}

	}

	@GetMapping("/status")
	public ResponseEntity<ResponseDTO<CategoryDTO>> getAllCategoryByStatus() {
		try {
			List<CategoryDTO> list = categoryService.getAllCategoryStatusFalse();
			if (list.isEmpty()) {
				return ResponseEntity
						.ok(new ResponseDTO<CategoryDTO>(new CategoryDTO(), " Not Found List", HttpStatus.NOT_FOUND));
			} else {
				ResponseDTO<CategoryDTO> responseLitsCategory = new ResponseDTO<CategoryDTO>(list, HttpStatus.OK);
				return ResponseEntity.ok(responseLitsCategory);
			}
		} catch (Exception e) {
			return ResponseEntity.ok(
					new ResponseDTO<CategoryDTO>(new CategoryDTO(), "Error Server", HttpStatus.INTERNAL_SERVER_ERROR));
		}

	}

	@PostMapping
	public ResponseEntity<ResponseDTO<CategoryDTO>> getByID(@RequestBody RequestDTO<CategoryDTO> requestDTO) {
		CategoryDTO categoryDTO = categoryService.getOneById(requestDTO);
		if (categoryDTO == null) {
			return ResponseEntity
					.ok(new ResponseDTO<CategoryDTO>(new CategoryDTO(), "Not Found", HttpStatus.NOT_FOUND));
		} else {
			return ResponseEntity.ok(new ResponseDTO<CategoryDTO>(categoryDTO, HttpStatus.OK));
		}
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseDTO<CategoryDTO>> update(@RequestBody RequestDTO<CategoryDTO> requestDTO)
			throws NotFoundException {
		return ResponseEntity
				.ok(new ResponseDTO<CategoryDTO>(categoryService.update(requestDTO), "Update Successfully",
						HttpStatus.CREATED));

	}

	@PostMapping("/add")
	public ResponseEntity<ResponseDTO<CategoryDTO>> add(@RequestBody RequestDTO<CategoryDTO> requestDTO) {
		System.out.println(requestDTO);
		if (categoryService.add(requestDTO)) {
			return ResponseEntity
					.ok(new ResponseDTO<CategoryDTO>(requestDTO.getData(), "Create Successfully", HttpStatus.CREATED));
		} else {
			return ResponseEntity
					.ok(new ResponseDTO<CategoryDTO>(new CategoryDTO(), "Create Fail", HttpStatus.NO_CONTENT));
		}
	}

	@PostMapping("/delete")
	public ResponseEntity<ResponseDTO<CategoryDTO>> deleteCategory(@RequestBody RequestDTO<CategoryDTO> requestDTO) {
		boolean isDeleted = categoryService.deleteCategory(requestDTO);
		if (isDeleted) {
			return ResponseEntity
					.ok(new ResponseDTO<CategoryDTO>(requestDTO.getData(), "Delete Successfully", HttpStatus.OK));
		} else {
			return ResponseEntity
					.ok(new ResponseDTO<CategoryDTO>(new CategoryDTO(), "Delete Fail", HttpStatus.NO_CONTENT));
		}
	}

	@PostMapping("/deleteSub")
	public ResponseEntity<ResponseDTO<CategoryDTO>> deleteSubCategory(@RequestBody RequestDTO<CategoryDTO> requestDTO)
			throws NotFoundException {
		return ResponseEntity
				.ok(new ResponseDTO<CategoryDTO>(categoryService.deleteSubCategory(requestDTO), "Update Successfully",
						HttpStatus.CREATED));

	}

	@PostMapping(value = "/search")
	public ResponseEntity<ResponseDTO<CategoryDTO>> getMethodName(@RequestBody RequestDTO<CategoryDTO> requestDTO) {
		return ResponseEntity.ok(new ResponseDTO<CategoryDTO>(categoryService.search(requestDTO), HttpStatus.OK));
	}
	
	@PostMapping("/findBySubCategory")
    public ResponseEntity<ResponseDTO<CategoryDTO>> findCategoryBySubCategory(
            @RequestBody RequestDTO<ProductDTO> requestDTO) {
        return ResponseEntity
                .ok(new ResponseDTO<CategoryDTO>(categoryService.getCategoryBySubCategory(requestDTO), HttpStatus.OK));
    }
    
    @PostMapping("/findCategory")
    public ResponseEntity<ResponseDTO<CategoryDTO>> findCategoryByCategory(
            @RequestBody RequestDTO<ProductDTO> requestDTO) {
        return ResponseEntity
                .ok(new ResponseDTO<CategoryDTO>(categoryService.getCategoryByCategory(requestDTO), HttpStatus.OK));
    }
}
