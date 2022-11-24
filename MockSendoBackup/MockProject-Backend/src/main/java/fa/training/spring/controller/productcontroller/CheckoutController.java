package fa.training.spring.controller.productcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.productdto.CheckoutDTO;
import fa.training.spring.dto.userdto.UserDTO;
import fa.training.spring.service.productservice.CheckoutService;

@RestController
@RequestMapping("/api/v1/checkout")
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<ResponseDTO<CheckoutDTO>> Checkout(@RequestBody RequestDTO<CheckoutDTO> requestDTO) {
        return ResponseEntity.ok(new ResponseDTO<CheckoutDTO>(checkoutService.checkout(requestDTO), HttpStatus.OK));
    }

    @PostMapping("/getByUsername")
    public ResponseEntity<ResponseDTO<CheckoutDTO>> getAll(@RequestBody RequestDTO<UserDTO> requestDTO) {
        return ResponseEntity.ok(new ResponseDTO<CheckoutDTO>(checkoutService.getall(requestDTO), HttpStatus.OK));
    }

    @PostMapping("/cancel")
    public ResponseEntity<ResponseDTO<CheckoutDTO>> cancel(@RequestBody RequestDTO<CheckoutDTO> requestDTO) {
        return ResponseEntity.ok(new ResponseDTO<CheckoutDTO>(checkoutService.cancel(requestDTO), HttpStatus.OK));
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDTO<CheckoutDTO>> delete(@RequestBody RequestDTO<CheckoutDTO> requestDTO) {
        return ResponseEntity.ok(new ResponseDTO<CheckoutDTO>(checkoutService.delete(requestDTO), HttpStatus.OK));
    }
}
