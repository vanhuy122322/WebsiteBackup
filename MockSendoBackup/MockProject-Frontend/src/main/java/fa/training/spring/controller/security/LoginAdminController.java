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
@RequestMapping("/admin/login")
public class LoginAdminController {

    @Autowired
    LoginService loginService;

    @GetMapping
    public String login(Model model) {
        return "pages/admin/LoginAdmin";
    }

    @PostMapping
    public String login(UserDTO userDTO, HttpServletResponse response, Model model) {
        RequestDTO<UserDTO> requestDTO = new RequestDTO<>(userDTO);
        loginService.login(requestDTO, response);
        return "redirect:/admin/user";
    }

}
