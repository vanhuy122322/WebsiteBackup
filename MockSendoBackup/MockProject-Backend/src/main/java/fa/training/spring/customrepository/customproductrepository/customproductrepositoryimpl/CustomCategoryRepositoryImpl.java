package fa.training.spring.customrepository.customproductrepository.customproductrepositoryimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import fa.training.spring.Util.RenderSlug;
import fa.training.spring.customrepository.customproductrepository.CustomCategoryRepository;
import fa.training.spring.entity.productentity.Category;
import fa.training.spring.entity.productentity.SubCategory;

@Repository
public class CustomCategoryRepositoryImpl implements CustomCategoryRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Category updateCategory(Category category) {
        try {
            Query query = new Query(new Criteria().andOperator(
                    Criteria.where("id").is(category.getId())));
            Update update = new Update();
            update.set("name", category.getName());
            update.set("updatedDate", LocalDateTime.now());
            List<SubCategory> subCategories = category.getSubCategories();
            subCategories.stream().forEach(e -> {
                if (e.getName() == "" || e.getName() == null) {
                    e.setDeleteStatus(true);
                }
                if (e.getCreatedDate() == null) {
                    e.setCreatedDate(LocalDateTime.now());
                }
                {
                    e.setUpdatedDate(LocalDateTime.now());
                }
                e.setSlug(RenderSlug.renderSlug(e.getName()));
            });
            update.set("subCategories", subCategories);
            mongoTemplate.updateMulti(query, update, Category.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(null, null);
        }
        return category;
    }

    public Category deleteSub(Category category) {
        for (SubCategory subCategory : category.getSubCategories()) {
            Query query = new Query(new Criteria().andOperator(
                    Criteria.where("id").is(
                            category.getId()),
                    Criteria.where("subCategories").elemMatch(Criteria.where("slug").is(subCategory.getSlug()))));
            Update update = new Update();
            update.set("subCategories.$.deleteStatus", true);
            mongoTemplate.updateMulti(query, update, Category.class);
        }
        return category;
    }
}
