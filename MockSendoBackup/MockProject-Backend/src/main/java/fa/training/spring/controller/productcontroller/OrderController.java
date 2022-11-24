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
import fa.training.spring.dto.productdto.OrderDTO;
import fa.training.spring.service.productservice.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
	@Autowired
	OrderService orderService;

	@GetMapping
	public ResponseEntity<ResponseDTO<OrderDTO>> getAll() {
		return ResponseEntity.ok(
				new ResponseDTO<OrderDTO>(
						orderService.getAll(),
						HttpStatus.OK));
	}

	@PostMapping
	public ResponseEntity<ResponseDTO<OrderDTO>> getByID(@RequestBody RequestDTO<OrderDTO> requestDTO) {
		return ResponseEntity.ok(
				new ResponseDTO<OrderDTO>(
						orderService.getOneById(requestDTO),
						HttpStatus.OK));
	}

	@PostMapping("/add")
	public ResponseEntity<ResponseDTO<OrderDTO>> add(@RequestBody RequestDTO<OrderDTO> requestDTO) {
		return ResponseEntity.ok(
				new ResponseDTO<OrderDTO>(
						orderService.add(requestDTO), "Create Successfully",
						HttpStatus.CREATED));

	}

	@PostMapping("/update")
	public ResponseEntity<ResponseDTO<OrderDTO>> update(@RequestBody RequestDTO<OrderDTO> requestDTO) {
		return ResponseEntity.ok(
				new ResponseDTO<OrderDTO>(
						orderService.update(requestDTO), "Update Successfully",
						HttpStatus.CREATED));

	}

	@PostMapping("/delete")
	public ResponseEntity<ResponseDTO<OrderDTO>> delete(@RequestBody RequestDTO<OrderDTO> requestDTO) {
		return ResponseEntity.ok(
				new ResponseDTO<OrderDTO>(
						orderService.delete(requestDTO), "Delete Successfully",
						HttpStatus.OK));
	}
}
