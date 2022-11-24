package fa.training.spring.entity.productentity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.github.slugify.Slugify;

import fa.training.spring.Util.RenderSlug;
import fa.training.spring.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Document("products")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Product extends AbstractEntity {
	private String name;
	private double price;
	private int amount;
	private String image;
	private String shopName;
	private String slug;
	private String category;
	private String subCategory;
	private ProductDetail productDetail;
}
