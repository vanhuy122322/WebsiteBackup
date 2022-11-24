package fa.training.spring.controller.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.user.UserDTO;
import fa.training.spring.service.user.UserService;

@Controller
@RequestMapping("/admin/signup")
public class RegisterController {

	@Autowired
	UserService userService;

	@GetMapping("")
	public String register() {
		return "pages/admin/Register";
	}

	@PostMapping("")
	public String register(UserDTO userDTO) {
		List<String> roleName = Arrays.asList("customer");
		userDTO.setRoleNames(roleName);
		RequestDTO<UserDTO> requestDTO = new RequestDTO<UserDTO>(userDTO);
		userService.registerUser(requestDTO);
		return "redirect:/";
	}
}
