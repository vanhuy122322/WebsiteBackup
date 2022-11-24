package fa.training.spring.entity.productentity;

import org.springframework.data.mongodb.core.mapping.Document;

import fa.training.spring.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Document(collection = "subcategories")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class SubCategory extends AbstractEntity {
	private String name;
	private String slug;
	private String image;
}
