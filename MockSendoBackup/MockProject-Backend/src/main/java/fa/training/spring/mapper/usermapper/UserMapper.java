package fa.training.spring.mapper.usermapper;

import java.util.List;

import org.springframework.stereotype.Component;

import fa.training.spring.dto.userdto.UserDTO;
import fa.training.spring.entity.userentity.User;
import fa.training.spring.mapper.AbstractMapper;


@Component
public class UserMapper extends AbstractMapper<User, UserDTO>{

    @Override
    public UserDTO mapToDTO(User s, Class<UserDTO> d) {
        return super.mapToDTO(s, d);
    }

    @Override
    public List<UserDTO> mapToListDTO(List<User> sList, Class<UserDTO> d) {
        return super.mapToListDTO(sList, d);
    }

    @Override
    public User mapToEntity(Class<User> s, UserDTO d) {
        return super.mapToEntity(s, d);
    }

    @Override
    public List<User> mapToListEntity(Class<User> s, List<UserDTO> dList) {
        return super.mapToListEntity(s, dList);
    }

    
}
