package fa.training.spring.repository.productrepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fa.training.spring.customrepository.customproductrepository.CustomProductDetailRepository;
import fa.training.spring.entity.productentity.ProductDetail;

@Repository
public interface ProductDetailRepository extends MongoRepository<ProductDetail, String>, CustomProductDetailRepository {

}
