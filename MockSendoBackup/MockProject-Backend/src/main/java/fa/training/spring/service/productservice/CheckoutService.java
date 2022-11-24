package fa.training.spring.service.productservice;

import java.util.List;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.CheckoutDTO;
import fa.training.spring.dto.userdto.UserDTO;

public interface CheckoutService {
    CheckoutDTO checkout(RequestDTO<CheckoutDTO> requestDTO);

    CheckoutDTO cancel(RequestDTO<CheckoutDTO> requestDTO);

    CheckoutDTO delete(RequestDTO<CheckoutDTO> requestDTO);

    List<CheckoutDTO> getall(RequestDTO<UserDTO> requestDTO);
    
    CheckoutDTO updateStatus(RequestDTO<CheckoutDTO> requestDTO);
}
