package fa.training.spring.dto.productdto;

import fa.training.spring.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ProductDTO extends AbstractDTO {
	private String name;
	private double price;
	private int amount;
	private String image;
	private String shopName;
	private String slug;
	private String category;
	private String subCategory;
	private ProductDetailDTO productDetail;

}
