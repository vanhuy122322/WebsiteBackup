package fa.training.spring.service.product;

import java.util.List;

import fa.training.spring.dto.product.CheckoutDTO;
import fa.training.spring.dto.user.UserDTO;

public interface CheckoutService {
    boolean checkout(CheckoutDTO checkoutDTO);

    List<CheckoutDTO> getAll(UserDTO userDTO);

    String cancel(CheckoutDTO checkoutDTO);

    String delete(CheckoutDTO checkoutDTO);
}
