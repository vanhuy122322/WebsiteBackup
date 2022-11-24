package fa.training.spring.dto.productdto;

import java.util.List;

import fa.training.spring.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class CartDTO extends AbstractDTO {
	private List<ProductInCartDTO> productInCarts;
	private String username;
	private double total;

}
