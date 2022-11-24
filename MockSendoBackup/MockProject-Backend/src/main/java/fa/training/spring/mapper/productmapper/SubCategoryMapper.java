package fa.training.spring.mapper.productmapper;

import java.util.List;

import org.springframework.stereotype.Component;

import fa.training.spring.dto.productdto.SubCategoryDTO;
import fa.training.spring.entity.productentity.SubCategory;
import fa.training.spring.mapper.AbstractMapper;

@Component
public class SubCategoryMapper extends AbstractMapper<SubCategory, SubCategoryDTO> {
	@Override
	public SubCategoryDTO mapToDTO(SubCategory s, Class<SubCategoryDTO> d) {
		return super.mapToDTO(s, d);
	}

	@Override
	public List<SubCategoryDTO> mapToListDTO(List<SubCategory> sList, Class<SubCategoryDTO> d) {
		return super.mapToListDTO(sList, d);
	}

	@Override
	public SubCategory mapToEntity(Class<SubCategory> s, SubCategoryDTO d) {
		return super.mapToEntity(s, d);
	}
	
	@Override
    public List<SubCategory> mapToListEntity(Class<SubCategory> s, List<SubCategoryDTO> dList) {
        return super.mapToListEntity(s, dList);
    }

}
