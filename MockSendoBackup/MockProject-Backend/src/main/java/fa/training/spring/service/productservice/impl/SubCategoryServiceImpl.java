package fa.training.spring.service.productservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.spring.Util.CustomMongoQuery;
import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.SubCategoryDTO;
import fa.training.spring.entity.productentity.SubCategory;
import fa.training.spring.mapper.productmapper.SubCategoryMapper;
import fa.training.spring.repository.productrepository.SubCategoryRepository;
import fa.training.spring.service.productservice.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
	@Autowired
	SubCategoryRepository subCategoryRepository;
	@Autowired
	SubCategoryMapper subCategoryMapper;
	@Autowired
	CustomMongoQuery<SubCategory> customRepository;

	@Override
	public List<SubCategoryDTO> getAll() {
		List<SubCategory> list = subCategoryRepository.findAll();
		return subCategoryMapper.mapToListDTO(list, SubCategoryDTO.class);
	}

	@Override
	public SubCategoryDTO getOneById(RequestDTO<SubCategoryDTO> req) {
		return subCategoryMapper.mapToDTO(subCategoryRepository.findById(req.getId()).get(), SubCategoryDTO.class);
	}

	@Override
	public boolean add(RequestDTO<SubCategoryDTO> req) {
		SubCategory createdSubCategory = subCategoryRepository
				.save(subCategoryMapper.mapToEntity(SubCategory.class, req.getData()));
		if (createdSubCategory != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean delete(RequestDTO<SubCategoryDTO> req) {
		customRepository.delete(req.getId(), SubCategory.class);
		System.out.println(subCategoryRepository.findById(req.getId()).get().isDeleteStatus());
		if (subCategoryRepository.findById(req.getId()).get().isDeleteStatus()) {
			return true;
		}
		return false;
	}

}
