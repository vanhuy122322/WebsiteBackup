package fa.training.spring.entity.productentity;

import org.springframework.data.mongodb.core.mapping.Document;

import fa.training.spring.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Document(collection = "productdetails")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ProductDetail extends AbstractEntity {
	private String title;
	private String description;
	private String material;
	private String origin;
	private String size;
	private String color;
}
