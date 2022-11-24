package fa.training.spring.service.productservice.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.spring.Util.CustomMongoQuery;
import fa.training.spring.Util.RenderSlug;
import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.CategoryDTO;
import fa.training.spring.dto.productdto.ProductDTO;
import fa.training.spring.entity.productentity.Category;
import fa.training.spring.entity.productentity.SubCategory;
import fa.training.spring.exception.NotFoundException;
import fa.training.spring.mapper.productmapper.CategoryMapper;
import fa.training.spring.mapper.productmapper.SubCategoryMapper;
import fa.training.spring.repository.productrepository.CategoryRepository;
import fa.training.spring.repository.productrepository.SubCategoryRepository;
import fa.training.spring.service.productservice.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CustomMongoQuery<Category> customRepository;

    @Autowired
    CustomMongoQuery<SubCategory> customMongoQuery;
    @Autowired
    SubCategoryMapper subCategoryMapper;
    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Override
    public List<CategoryDTO> getAll() {
        List<Category> list = categoryRepository.findAllCategories();
        return categoryMapper.mapToListDTO(list, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategoryStatusFalse() {
        List<Category> list = categoryRepository.findAllCategories();
        return categoryMapper.mapToListDTO(list, CategoryDTO.class);
    }

    @Override
    public CategoryDTO getOneById(RequestDTO<CategoryDTO> req) {
        return categoryMapper.mapToDTO(categoryRepository.findById(req.getId()).get(), CategoryDTO.class);
    }

    @Override
    public boolean add(RequestDTO<CategoryDTO> req) {
        req.getData().setSlug(RenderSlug.renderSlug(req.getData().getName()));
        Category category = categoryMapper.mapToEntity(Category.class, req.getData());
        if (category.getSubCategories() != null) {
            List<SubCategory> subCategories = category.getSubCategories();
            for (SubCategory subCategory : subCategories) {
                subCategory.setCreatedDate(LocalDateTime.now());
                subCategory.setSlug(RenderSlug.renderSlug(subCategory.getName()));
            }
            category.setSubCategories(subCategories);
        }
        return categoryRepository.save(category) != null;
    }

    @Override
    public CategoryDTO update(RequestDTO<CategoryDTO> req) throws NotFoundException {
        return categoryMapper.mapToDTO(
                categoryRepository.updateCategory(categoryMapper.mapToEntity(Category.class, req.getData())),
                CategoryDTO.class);
    }

    public CategoryDTO handleFindCategory(CategoryDTO categoryDTO, String message) throws NotFoundException {
        if (categoryDTO != null) {
            return categoryDTO;
        } else {
            throw new NotFoundException("Product not found");
        }
    }

    @Override
    public boolean deleteCategory(RequestDTO<CategoryDTO> req) {
        customRepository.delete(req.getId(), Category.class);
        return categoryRepository.findById(req.getId()).get().isDeleteStatus();
    }

    @Override
    public CategoryDTO deleteSubCategory(RequestDTO<CategoryDTO> req) {
        return categoryMapper.mapToDTO(
                categoryRepository.deleteSub(categoryMapper.mapToEntity(Category.class, req.getData())),
                CategoryDTO.class);

    }

    @Override
    public CategoryDTO search(RequestDTO<CategoryDTO> requestDTO) {
        CategoryDTO categoryDTO = categoryMapper.mapToDTO(categoryRepository.findByName(requestDTO.getData().getName()),
                CategoryDTO.class);
        if (categoryDTO != null) {
            return categoryDTO;
        } else {
            throw new NullPointerException();
        }
    }
    
    @Override
    public CategoryDTO getCategoryBySubCategory(RequestDTO<ProductDTO> requestDTO) {
        CategoryDTO categoryDTO = categoryMapper.mapToDTO(
                categoryRepository.findBySubCategoriesNameLike(requestDTO.getData().getSubCategory()),
                CategoryDTO.class);
        System.out.println(categoryDTO);
        return categoryDTO;
    }

    @Override
    public CategoryDTO getCategoryByCategory(RequestDTO<ProductDTO> requestDTO) {
        CategoryDTO categoryDTO = categoryMapper.mapToDTO(
                categoryRepository.findByName(requestDTO.getData().getCategory()),
                CategoryDTO.class);
        return categoryDTO;
    }

}
