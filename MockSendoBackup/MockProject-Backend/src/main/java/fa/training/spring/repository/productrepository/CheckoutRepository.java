package fa.training.spring.repository.productrepository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fa.training.spring.customrepository.customproductrepository.CustomCheckoutRepository;
import fa.training.spring.entity.productentity.Checkout;

@Repository
public interface CheckoutRepository extends MongoRepository<Checkout, String>, CustomCheckoutRepository {
    
    Checkout findByCheckoutCode(String id);
    
    List<Checkout> findByUsername(String username);

    void deleteByCheckoutCode(String checkoutCode);
    
    List<Checkout> findByShopName(String shopName);
}
