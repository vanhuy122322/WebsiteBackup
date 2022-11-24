package fa.training.spring.dto.productdto;

import fa.training.spring.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ProductInCartDTO extends AbstractDTO {
	private String shopName;
	private String name;
	private String image;
	private String size;
	private String color;
	private double price;
	private int quantity;
	private boolean chooseStatus;
}
