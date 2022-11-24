package fa.training.spring.entity.productentity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import fa.training.spring.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Document("checkouts")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Checkout extends AbstractEntity {
	private String checkoutCode;
	private String username;
	private String phone;
	private String location;
	private String methodDelivery;
	private String methodPayment;
	private String voucher;
	private String shopName;
	private String status;
	List<ProductInCart> productInCarts;
	private Double totalProduct;
	private Double totalDelivery;
	private Double totalCheckout;
}
