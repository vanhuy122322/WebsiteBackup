package fa.training.spring.entity.productentity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import fa.training.spring.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Document(collection = "categories")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Category extends AbstractEntity {
	private String name;
	private String slug;
	private List<SubCategory> subCategories = new ArrayList<>();
}
