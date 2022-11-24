package fa.training.spring.dto.productdto;

import java.util.List;

import fa.training.spring.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class CategoryDTO extends AbstractDTO {
	private String name;
	private String slug;
	private List<SubCategoryDTO> subCategories;
}
