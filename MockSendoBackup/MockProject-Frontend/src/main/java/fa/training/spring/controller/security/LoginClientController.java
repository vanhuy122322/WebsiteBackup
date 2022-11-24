package fa.training.spring.controller.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.user.UserDTO;
import fa.training.spring.service.login.LoginService;

@Controller
@RequestMapping("/login")
public class LoginClientController {

    @Autowired
    LoginService loginService;

    @PostMapping
    public String loginClient(UserDTO userDTO, HttpServletResponse response, Model model) {
        RequestDTO<UserDTO> requestDTO = new RequestDTO<>(userDTO);
        loginService.loginClient(requestDTO, response, model);
        return "redirect:/home";
    }

}
