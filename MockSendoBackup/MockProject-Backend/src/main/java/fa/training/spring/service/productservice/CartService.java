package fa.training.spring.service.productservice;

import java.util.List;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.CartDTO;

public interface CartService {
    boolean delete(RequestDTO<CartDTO> requestDTO);

    CartDTO update(RequestDTO<CartDTO> requestDTO);

    CartDTO add(RequestDTO<CartDTO> requestDTO);

    CartDTO deleteProductInCart(RequestDTO<CartDTO> requestDTO);

    CartDTO getOneById(RequestDTO<CartDTO> requestDTO);

    CartDTO getOneByUsername(RequestDTO<CartDTO> requestDTO);

    List<CartDTO> getAll();
}