package fa.training.spring.repository.productrepository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import fa.training.spring.customrepository.customproductrepository.CustomCategoryRepository;
import fa.training.spring.entity.productentity.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String>, CustomCategoryRepository {

	List<Category> findAll();

	Category findByName(String name);

	@Aggregation(pipeline = {
			"{$match: {deleteStatus : false}}",
			"{$sort: {createdDate: -1}}"
	})
	List<Category> findAllCategories();

	@Query(" {list.id: ?0}")
	@Update("{$set : { 'deleteStatus': false}}")
	void updateDeleteStatusById(String id);
	
	Category findBySubCategoriesNameLike(String name);
	
}
