package fa.training.spring.repository.shoprepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fa.training.spring.entity.shopentity.Shop;

@Repository
public interface ShopRepository extends MongoRepository<Shop, String> {

    Shop findShopBySlug(String slug);
    
    Shop findByName(String name);
}
