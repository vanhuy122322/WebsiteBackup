package fa.training.spring.service.userservice;

import java.util.List;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.shopdto.ShopDTO;
import fa.training.spring.dto.userdto.FormChangePassword;
import fa.training.spring.dto.userdto.JwtResponse;
import fa.training.spring.dto.userdto.LocationDTO;
import fa.training.spring.dto.userdto.UserDTO;

public interface UserService {

    ResponseDTO<JwtResponse> login(RequestDTO<UserDTO> requestDTO);

    List<UserDTO> getUsers();

    UserDTO findUserById(RequestDTO<UserDTO> requestDTO);

    UserDTO findByUsername(RequestDTO<UserDTO> requestDTO);

    ResponseDTO<UserDTO> createUser(RequestDTO<UserDTO> requestDTO);

    ResponseDTO<UserDTO> addRoleToUser(RequestDTO<UserDTO> requestDTO);

    ResponseDTO<UserDTO> changePassword(RequestDTO<FormChangePassword> requestDTO);

    ResponseDTO<UserDTO> updateUser(RequestDTO<UserDTO> requestDTO);

    ResponseDTO<UserDTO> addAddressToUser(RequestDTO<LocationDTO> requestDTO);

    String deleteUser(RequestDTO<UserDTO> requestDTO);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
    
   

}
