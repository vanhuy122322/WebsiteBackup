package fa.training.spring.controller.client.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fa.training.spring.dto.product.CheckoutDTO;
import fa.training.spring.service.product.CheckoutService;

@Controller
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;

    @GetMapping("/checkout")
    public String CheckoutPage() {
        return "pages/client/cart/Checkout";
    }

    @PostMapping("/checkout")
    public String Checkout(CheckoutDTO checkoutDTO) {
        if (checkoutService.checkout(checkoutDTO)) {
            return "redirect:/customer/order/wait-accept";
        } else {
            return "redirect:/checkout";
        }
    }
}
