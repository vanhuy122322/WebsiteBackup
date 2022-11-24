package fa.training.spring.service.userservice;

import fa.training.spring.dto.userdto.RoleDTO;
import fa.training.spring.entity.userentity.RoleName;

public interface RoleService {
    RoleDTO findRoleByName(String name);
}
