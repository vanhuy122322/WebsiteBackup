package fa.training.spring.repository.productrepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fa.training.spring.entity.productentity.Order;

public interface OrderRepository extends MongoRepository<Order, String>{
    Order findOneById(String id);
    Order findOneByOrderCode(String orderCode);
}
