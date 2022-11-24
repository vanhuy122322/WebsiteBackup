package fa.training.spring.dto.productdto;

import fa.training.spring.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ProductDetailDTO extends AbstractDTO {
	private String title;
	private String description;
	private String material;
	private String origin;
	private String size;
	private String color;
}
