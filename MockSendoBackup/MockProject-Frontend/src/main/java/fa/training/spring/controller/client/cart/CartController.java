package fa.training.spring.controller.client.cart;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.product.CartDTO;
import fa.training.spring.dto.product.ProductInCartDTO;
import fa.training.spring.service.cart.CartService;

@Controller
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/cart")
    public String CartPage(Model model) {
        cartService.getById(model, null);
        return "/pages/client/cart/Cart";
    }

    @PostMapping("/add-product-to-cart")
    @ResponseBody
    public String AddProductToCart(ProductInCartDTO productInCartDTO) {
        return cartService.addProductToCart(productInCartDTO);
    }

    @PostMapping("/delete-product-in-cart")
    @ResponseBody
    public String DeleteProductInCart(RequestDTO<CartDTO> requestDTO) {
        return cartService.deleteProductInCart(requestDTO);
    }

    @PostMapping("/buy-now")
    public String Checkout(CartDTO cartDTO, Double total, Model model) {
        List<ProductInCartDTO> productInCarts = cartDTO.getProductInCarts().stream().filter(e -> e.isChooseStatus())
                .collect(Collectors.toList());
        model.addAttribute("productInCarts", productInCarts);
        model.addAttribute("total", total);
        return "pages/client/cart/Checkout";
    }
}
