package fa.training.spring.repository.userrepository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fa.training.spring.entity.userentity.Location;

@Repository
public interface LocationRepository extends MongoRepository<Location, String>{

    List<Location> findAllLocationByUsername(String username);
}
