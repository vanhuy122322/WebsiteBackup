package fa.training.spring.repository.userrepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fa.training.spring.entity.userentity.Role;
import fa.training.spring.entity.userentity.RoleName;

public interface RoleRepository extends MongoRepository<Role, String>{
    
    Role findRoleByName(RoleName name);
    
    Role findRoleByName(String name);

}
