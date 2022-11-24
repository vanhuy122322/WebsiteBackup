package fa.training.spring.dto.productdto;

import java.util.List;

import fa.training.spring.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class CheckoutDTO extends AbstractDTO {
    private String checkoutCode;
    private String username;
    private String phone;
    private String location;
    private String methodDelivery;
    private String methodPayment;
    private String voucher;
    private String shopName;
    private String status;
    List<ProductInCartDTO> productInCarts;
    private Double totalProduct;
    private Double totalDelivery;
    private Double totalCheckout;
}
