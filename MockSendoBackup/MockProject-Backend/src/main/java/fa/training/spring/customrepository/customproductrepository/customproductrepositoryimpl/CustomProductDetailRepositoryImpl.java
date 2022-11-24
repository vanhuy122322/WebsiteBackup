package fa.training.spring.customrepository.customproductrepository.customproductrepositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import fa.training.spring.customrepository.customproductrepository.CustomProductDetailRepository;
import fa.training.spring.entity.productentity.ProductDetail;

@Repository
public class CustomProductDetailRepositoryImpl implements CustomProductDetailRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void updateProductDetail(ProductDetail productDetail) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(productDetail.getId()));
        Update update = new Update();
        update.set("title", productDetail.getTitle());
        update.set("description", productDetail.getDescription());
        update.set("material", productDetail.getMaterial());
        update.set("origin", productDetail.getOrigin());
        update.set("size", productDetail.getSize());
        update.set("color", productDetail.getColor());
        mongoTemplate.updateMulti(query, update, ProductDetail.class);
    }

}
