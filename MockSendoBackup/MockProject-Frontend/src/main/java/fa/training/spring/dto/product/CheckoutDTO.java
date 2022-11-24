package fa.training.spring.dto.product;

import java.util.List;
import java.util.Random;

import fa.training.spring.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class CheckoutDTO extends AbstractDTO {
    private String checkoutCode = "#" + new Random().nextInt(999999999);
    private String username;
    private String phone;
    private String location;
    private String methodDelivery;
    private String methodPayment;
    private String shopName;
    private String voucher;
    private String status = "Chờ xác nhận";
    List<ProductInCartDTO> productInCarts;
    private Double totalProduct;
    private Double totalDelivery;
    private Double totalCheckout;
}
