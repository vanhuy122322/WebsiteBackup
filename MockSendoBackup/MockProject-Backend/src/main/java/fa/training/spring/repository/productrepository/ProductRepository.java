package fa.training.spring.repository.productrepository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fa.training.spring.customrepository.customproductrepository.CustomProductRepository;
import fa.training.spring.entity.productentity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>, CustomProductRepository {

	Product findByName(String name);

	List<Product> findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCaseAndSubCategoryContainingIgnoreCase(
			String name, String category,
			String subCategoryName);

	@Aggregation(pipeline = {
			"{$match: {deleteStatus : false}}",
			"{$sort: {createdDate: -1}}"
	})
	List<Product> findAll();
	
	List<Product> findByCategoryOrSubCategory(String category,String subCategory);

	Product findProductBySlug(String slug);

	// List<Product>
	// findByCategoryLikeAndProductDetailColorLikeAndProductDetailSizeLikeAndProductDetailMaterialLikeAndProductDetailOriginLikeAndPriceBetween(String
	// category, String color, String size,String material, String origin,double
	// from,double to);
	//
	// List<Product>
	// findBySubCategoryLikeAndProductDetailColorLikeAndProductDetailSizeLikeAndProductDetailMaterialLikeAndProductDetailOriginLikeAndPriceBetween(String
	// subcategory, String color, String size,String material, String origin,double
	// from,double to);

	List<Product> findBySubCategory(String subCategoryName);
	
	List<Product> findByCategory(String category);
	
	List<Product> findByShopNameAndDeleteStatus(String name,boolean status);

}
