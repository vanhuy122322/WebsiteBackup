package fa.training.spring.mapper.productmapper;

import java.util.List;

import org.springframework.stereotype.Component;

import fa.training.spring.dto.productdto.CategoryDTO;
import fa.training.spring.entity.productentity.Category;
import fa.training.spring.mapper.AbstractMapper;

@Component
public class CategoryMapper extends AbstractMapper<Category, CategoryDTO> {
	@Override
	public CategoryDTO mapToDTO(Category s, Class<CategoryDTO> d) {
		return super.mapToDTO(s, d);
	}

	@Override
	public List<CategoryDTO> mapToListDTO(List<Category> sList, Class<CategoryDTO> d) {
		return super.mapToListDTO(sList, d);
	}

	@Override
	public Category mapToEntity(Class<Category> s, CategoryDTO d) {
		return super.mapToEntity(s, d);
	}

}
