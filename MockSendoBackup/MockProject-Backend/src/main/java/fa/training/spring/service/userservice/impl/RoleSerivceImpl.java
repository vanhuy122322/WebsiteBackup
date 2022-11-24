package fa.training.spring.service.userservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.spring.dto.userdto.RoleDTO;
import fa.training.spring.entity.userentity.RoleName;
import fa.training.spring.mapper.usermapper.RoleMapper;
import fa.training.spring.repository.userrepository.RoleRepository;
import fa.training.spring.service.userservice.RoleService;

@Service
public class RoleSerivceImpl implements RoleService{
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    RoleMapper mapper;

    @Override
    public RoleDTO findRoleByName(String name) {
        return mapper.mapToDTO(roleRepository.findRoleByName(name), RoleDTO.class) ;
    }

}
