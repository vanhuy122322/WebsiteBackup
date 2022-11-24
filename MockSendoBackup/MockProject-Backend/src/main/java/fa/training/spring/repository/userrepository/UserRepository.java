package fa.training.spring.repository.userrepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fa.training.spring.entity.userentity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);

    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    User findUserByShopName(String shopName);
}
