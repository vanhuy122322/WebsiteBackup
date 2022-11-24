package fa.training.spring.repository.productrepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fa.training.spring.entity.productentity.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    Cart findByUsername(String username);
}
