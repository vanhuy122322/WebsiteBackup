package fa.training.spring.entity.productentity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import fa.training.spring.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Document("carts")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Cart extends AbstractEntity {
	private List<ProductInCart> productInCarts;
	private String username;
	private double total;
}
