package fa.training.spring.controller.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.spring.service.login.LogoutService;

@Controller
@RequestMapping("/admin/logout")
public class LogoutAdminController {
	@Autowired
	LogoutService logoutService;

	@GetMapping
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		logoutService.logout(req, resp);
		return "redirect:/admin/login";
	}
	
	
}
