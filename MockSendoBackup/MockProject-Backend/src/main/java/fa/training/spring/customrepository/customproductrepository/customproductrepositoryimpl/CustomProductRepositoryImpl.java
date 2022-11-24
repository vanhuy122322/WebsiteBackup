package fa.training.spring.customrepository.customproductrepository.customproductrepositoryimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import fa.training.spring.customrepository.customproductrepository.CustomProductRepository;
import fa.training.spring.entity.productentity.Product;

@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Product updateProduct(Product product) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(product.getId()));
            Update update = new Update();
            update.set("name", product.getName());
            update.set("price", product.getPrice());
            update.set("amount", product.getAmount());
            update.set("image", product.getImage());
            update.set("updatedDate", LocalDateTime.now());
            update.set("productDetail.title", product.getProductDetail().getTitle());
            update.set("productDetail.description", product.getProductDetail().getDescription());
            update.set("productDetail.material", product.getProductDetail().getMaterial());
            update.set("productDetail.origin", product.getProductDetail().getOrigin());
            update.set("productDetail.size", product.getProductDetail().getSize());
            update.set("productDetail.color", product.getProductDetail().getColor());
            update.set("subCategory", product.getSubCategory());
            update.set("category", product.getCategory());
            mongoTemplate.updateMulti(query, update, Product.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(null, null);
        }
        return product;
    }
    public List<Product> findBeetwenPrice(String category,String subcategory,double startPrice, double endPrice){
        System.out.println(category);
        System.out.println(subcategory);
        System.out.println(startPrice);
        System.out.println(endPrice);
        try {
            Query query = new Query();
            query.addCriteria(
                    Criteria.where("").orOperator(
                            Criteria.where("category").is(category).and("price").gte(startPrice).and("price").lt(endPrice),
                            Criteria.where("subCategory").is(subcategory).and("price").gte(startPrice).and("price").lt(endPrice)
                    )
                );
            return mongoTemplate.find(query, Product.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(null, null);
        }
    }
}
