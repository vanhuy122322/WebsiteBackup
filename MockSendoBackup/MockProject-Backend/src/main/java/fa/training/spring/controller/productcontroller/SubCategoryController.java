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
import fa.training.spring.dto.productdto.SubCategoryDTO;
import fa.training.spring.service.productservice.SubCategoryService;

@RestController
@RequestMapping("/api/v1/subCategory")
public class SubCategoryController {

	@Autowired
	SubCategoryService subCategoryService;

	@GetMapping
	public ResponseEntity<ResponseDTO<SubCategoryDTO>> getAll() {
		try {
			List<SubCategoryDTO> list = subCategoryService.getAll();

			if (list.isEmpty()) {
				return ResponseEntity.ok(
						new ResponseDTO<SubCategoryDTO>(new SubCategoryDTO(), "Not Found List", HttpStatus.NOT_FOUND));
			} else {
				ResponseDTO<SubCategoryDTO> responseLitsCategory = new ResponseDTO<SubCategoryDTO>(list, HttpStatus.OK);
				return ResponseEntity.ok(responseLitsCategory);
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseDTO<SubCategoryDTO>(new SubCategoryDTO(), "Error Server",
					HttpStatus.INTERNAL_SERVER_ERROR));
		}

	}

	@PostMapping
	public ResponseEntity<ResponseDTO<SubCategoryDTO>> getByID(@RequestBody RequestDTO<SubCategoryDTO> requestDTO) {
		SubCategoryDTO categoryDTO = subCategoryService.getOneById(requestDTO);
		if (categoryDTO == null) {
			return ResponseEntity
					.ok(new ResponseDTO<SubCategoryDTO>(new SubCategoryDTO(), "Not Found", HttpStatus.NOT_FOUND));
		} else {
			return ResponseEntity.ok(new ResponseDTO<SubCategoryDTO>(categoryDTO, HttpStatus.OK));
		}
	}

	@PostMapping("/add")
	public ResponseEntity<ResponseDTO<SubCategoryDTO>> add(@RequestBody RequestDTO<SubCategoryDTO> requestDTO) {
		if (subCategoryService.add(requestDTO)) {
			return ResponseEntity.ok(
					new ResponseDTO<SubCategoryDTO>(requestDTO.getData(), "Create Successfully", HttpStatus.CREATED));
		} else {
			return ResponseEntity
					.ok(new ResponseDTO<SubCategoryDTO>(new SubCategoryDTO(), "Create Fail", HttpStatus.NO_CONTENT));
		}
	}

	@PostMapping("/delete")
	public ResponseEntity<ResponseDTO<SubCategoryDTO>> delete(@RequestBody RequestDTO<SubCategoryDTO> requestDTO) {
		boolean isDeleted = subCategoryService.delete(requestDTO);
		if (isDeleted) {
			return ResponseEntity
					.ok(new ResponseDTO<SubCategoryDTO>(requestDTO.getData(), "Delete Successfully", HttpStatus.OK));
		} else {
			return ResponseEntity
					.ok(new ResponseDTO<SubCategoryDTO>(new SubCategoryDTO(), "Delete Fail", HttpStatus.NO_CONTENT));
		}

	}
}
