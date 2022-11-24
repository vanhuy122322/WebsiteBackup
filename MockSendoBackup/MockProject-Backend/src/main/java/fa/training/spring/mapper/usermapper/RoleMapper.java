package fa.training.spring.mapper.usermapper;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import fa.training.spring.dto.userdto.RoleDTO;
import fa.training.spring.entity.userentity.Role;
import fa.training.spring.mapper.AbstractMapper;

@Component
public class RoleMapper extends AbstractMapper<Role, RoleDTO>{

    @Override
    public RoleDTO mapToDTO(Role s, Class<RoleDTO> d) {
        // TODO Auto-generated method stub
        return super.mapToDTO(s, d);
    }

    @Override
    public List<RoleDTO> mapToListDTO(List<Role> sList, Class<RoleDTO> d) {
        // TODO Auto-generated method stub
        return super.mapToListDTO(sList, d);
    }

    @Override
    public Role mapToEntity(Class<Role> s, RoleDTO d) {
        // TODO Auto-generated method stub
        return super.mapToEntity(s, d);
    }

    @Override
    public List<Role> mapToListEntity(Class<Role> s, List<RoleDTO> dList) {
        // TODO Auto-generated method stub
        return super.mapToListEntity(s, dList);
    }
    
    public Set<Role> mapToSetEntity(Set<RoleDTO> roleDTOs){
        Set<Role> roles = new HashSet<>();
        Iterator<RoleDTO> iterator = roleDTOs.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next().getName());
            roles.add(new Role(iterator.next().getName()));
        }
        return roles;
        
    }

}
