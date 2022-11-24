package fa.training.spring.service.cart;

import org.springframework.ui.Model;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.product.CartDTO;
import fa.training.spring.dto.product.ProductInCartDTO;

public interface CartService {

    void getById(Model model, RequestDTO<ProductInCartDTO> requestDTO);

    String addProductToCart(ProductInCartDTO productInCartDTO);

    String deleteProductInCart(RequestDTO<CartDTO> requestDTO);
}
