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
import fa.training.spring.dto.productdto.CartDTO;
import fa.training.spring.service.productservice.CartService;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
	@Autowired
	CartService cartService;

	@GetMapping
	public ResponseEntity<ResponseDTO<CartDTO>> getAll() {
		return ResponseEntity.ok(new ResponseDTO<CartDTO>(
				cartService.getAll(), HttpStatus.OK));
	}

	@PostMapping
	public ResponseEntity<ResponseDTO<CartDTO>> getByID(@RequestBody RequestDTO<CartDTO> requestDTO) {
		return ResponseEntity
				.ok(new ResponseDTO<CartDTO>(cartService.getOneById(requestDTO), HttpStatus.OK));
	}

	@PostMapping("/get-by-username")
	public ResponseEntity<ResponseDTO<CartDTO>> getByUsername(@RequestBody RequestDTO<CartDTO> requestDTO) {
		return ResponseEntity
				.ok(new ResponseDTO<CartDTO>(cartService.getOneByUsername(requestDTO), HttpStatus.OK));
	}

	@PostMapping("/add-product-to-cart")
	public ResponseEntity<ResponseDTO<CartDTO>> add(@RequestBody RequestDTO<CartDTO> requestDTO) {
		return ResponseEntity
				.ok(new ResponseDTO<CartDTO>(cartService.add(requestDTO), "Create Successfully",
						HttpStatus.CREATED));
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseDTO<CartDTO>> update(@RequestBody RequestDTO<CartDTO> requestDTO) {
		return ResponseEntity
				.ok(new ResponseDTO<CartDTO>(cartService.update(requestDTO), "Update Successfully",
						HttpStatus.CREATED));

	}

	@PostMapping("/delete")
	public ResponseEntity<ResponseDTO<CartDTO>> delete(@RequestBody RequestDTO<CartDTO> requestDTO) {
		boolean isDeleted = cartService.delete(requestDTO);
		if (isDeleted) {
			return ResponseEntity
					.ok(new ResponseDTO<CartDTO>(requestDTO.getData(), "Delete Successfully", HttpStatus.OK));
		} else {
			return ResponseEntity
					.ok(new ResponseDTO<CartDTO>(new CartDTO(), "Delete Fail", HttpStatus.NO_CONTENT));
		}

	}

	@PostMapping("/delete-product-in-cart")
	public ResponseEntity<ResponseDTO<CartDTO>> deleteProductInCart(@RequestBody RequestDTO<CartDTO> requestDTO) {
		return ResponseEntity
				.ok(new ResponseDTO<CartDTO>(cartService.deleteProductInCart(requestDTO), "Create Successfully",
						HttpStatus.CREATED));
	}
}
