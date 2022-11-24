package fa.training.spring.customrepository.customproductrepository.customproductrepositoryimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import fa.training.spring.customrepository.customproductrepository.CustomCheckoutRepository;
import fa.training.spring.entity.productentity.Checkout;
import fa.training.spring.entity.productentity.Product;

@Repository
public class CustomCheckoutRepositoryImpl implements CustomCheckoutRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Checkout checkoutCancel(Checkout checkout) {
        Query query = new Query();
        query.addCriteria(Criteria.where("checkoutCode").is(checkout.getCheckoutCode()));
        Update update = new Update();
        update.set("status", "Đã hủy");
        mongoTemplate.updateMulti(query, update, Checkout.class);
        return checkout;
    }
    
    @Override
    public Checkout updateStatusCheckout(Checkout checkout) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("checkoutCode").is(checkout.getCheckoutCode()));
            Update update = new Update();
            update.set("status", checkout.getStatus());
            mongoTemplate.updateMulti(query, update, Checkout.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(null, null);
        }
        return checkout;
    }

}
