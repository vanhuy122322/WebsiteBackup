package fa.training.spring.customrepository.customproductrepository.customproductrepositoryimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import fa.training.spring.customrepository.customproductrepository.CustomSubCategoryRepository;
import fa.training.spring.entity.productentity.Category;
import fa.training.spring.entity.productentity.SubCategory;

@Repository
public class CustomSubCategoryRepositoryImpl implements CustomSubCategoryRepository {
    
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public SubCategory updateSubCategory(SubCategory subCategory) {
        try {
            Query query = new Query(new Criteria().andOperator(
                    Criteria.where("id").is(subCategory.getId())
                  ));
            Update update = new Update();
            update.set("name", subCategory.getName());
            update.set("updatedDate", LocalDateTime.now());
            mongoTemplate.updateMulti(query, update, SubCategory.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(null, null);
        }
        return subCategory;
    }

}
