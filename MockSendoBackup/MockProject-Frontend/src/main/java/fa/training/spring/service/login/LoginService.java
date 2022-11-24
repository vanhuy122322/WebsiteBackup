package fa.training.spring.service.login;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.user.UserDTO;

public interface LoginService {
    void login(RequestDTO<UserDTO> requestDTO, HttpServletResponse response);

    void loginClient(RequestDTO<UserDTO> requestDTO, HttpServletResponse response, Model model);
}
