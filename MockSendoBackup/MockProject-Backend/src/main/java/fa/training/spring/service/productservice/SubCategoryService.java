package fa.training.spring.service.productservice;

import java.util.List;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.SubCategoryDTO;

public interface SubCategoryService {

	boolean delete(RequestDTO<SubCategoryDTO> req);

	boolean add(RequestDTO<SubCategoryDTO> req);

	SubCategoryDTO getOneById(RequestDTO<SubCategoryDTO> req);

	List<SubCategoryDTO> getAll();
	
}
