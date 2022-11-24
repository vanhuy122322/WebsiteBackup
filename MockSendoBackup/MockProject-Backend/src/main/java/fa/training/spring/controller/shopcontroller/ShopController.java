package fa.training.spring.controller.shopcontroller;

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
import fa.training.spring.dto.productdto.ProductDTO;
import fa.training.spring.dto.shopdto.ShopDTO;
import fa.training.spring.dto.userdto.UserDTO;
import fa.training.spring.service.productservice.CheckoutService;
import fa.training.spring.service.shopservice.ShopService;
import fa.training.spring.service.userservice.UserService;

@RestController
@RequestMapping("/api/v1/shop")
public class ShopController {

    @Autowired
    ShopService shopService;
    
    @Autowired
    CheckoutService checkoutService;
    
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<ShopDTO>> add(@RequestBody RequestDTO<ShopDTO> requestDTO) {
        return ResponseEntity
                .ok(new ResponseDTO<ShopDTO>(shopService.add(requestDTO), "Create Successfully",
                        HttpStatus.CREATED));
    }

    @PostMapping("/findByName")
    public ResponseEntity<ResponseDTO<ShopDTO>> findByName(@RequestBody RequestDTO<UserDTO> requestDTO) {
        return ResponseEntity.ok(new ResponseDTO<ShopDTO>(shopService.getOneByName(requestDTO), HttpStatus.OK));
    }
    
    @PostMapping("/findCheckoutByShopName")
    public ResponseEntity<ResponseDTO<CheckoutDTO>> getProductByShopName(@RequestBody RequestDTO<ShopDTO> requestDTO) {
        return ResponseEntity.ok(new ResponseDTO<CheckoutDTO>(shopService.getCheckoutByShopName(requestDTO), HttpStatus.OK));
    }
    
    @PostMapping("/updateStatusCheckout")
    public ResponseEntity<ResponseDTO<CheckoutDTO>> updateStatusCheckout(@RequestBody RequestDTO<CheckoutDTO> requestDTO) {
        return ResponseEntity.ok(new ResponseDTO<CheckoutDTO>(checkoutService.updateStatus(requestDTO), HttpStatus.OK));
    }
    
    @PostMapping("/findByCheckoutCode")
    public ResponseEntity<ResponseDTO<CheckoutDTO>> findByCheckoutCode(@RequestBody RequestDTO<CheckoutDTO> requestDTO) {
        return ResponseEntity.ok(new ResponseDTO<CheckoutDTO>(shopService.getOneByCheckOutCode(requestDTO), HttpStatus.OK));
    }

  

}
