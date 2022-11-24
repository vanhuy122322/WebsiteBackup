package fa.training.spring.service.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.user.FormChangePassword;
import fa.training.spring.dto.user.UserDTO;

public interface UserService {
    void getUsers(Model model);

    void add(RequestDTO<UserDTO> requestDTO);

    void delete(String id);

    void update(RequestDTO<UserDTO> requestDTO);

    ResponseDTO<UserDTO> changePassword(RequestDTO<FormChangePassword> requestDTO);

    void getUser(String id, Model model);
    
    ResponseDTO<UserDTO> getUserByUsername(HttpServletRequest request, Model model);
    
    ResponseDTO<UserDTO> updateCustomer(RequestDTO<UserDTO> requestDTO);

	ResponseDTO<UserDTO> changePasswordCustomer(RequestDTO<FormChangePassword> requestDTO);
	
	ResponseDTO<UserDTO> addRoleToUser(RequestDTO<UserDTO> requestDTO);
	
	public ResponseDTO<UserDTO> registerUser(RequestDTO<UserDTO> requestDTO);
}
