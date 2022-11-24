package fa.training.spring.repository.productrepository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.spring.entity.productentity.SubCategory;

@Repository
public interface SubCategoryRepository extends MongoRepository<SubCategory, String> {
	@Query("{'deleteStatus' : false}")
	List<SubCategory> findAll();
}
