package fa.training.spring.dto.product;

import fa.training.spring.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class SubCategoryDTO extends AbstractDTO {
	private String name;
	private String slug;
	private String image;
}
