package fa.training.spring.service.login.Impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.user.JwtResponseDTO;
import fa.training.spring.dto.user.UserDTO;
import fa.training.spring.service.login.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private final static String API_LOGIN = "http://localhost:8080/api/v1/signin";

	@Autowired
	RestTemplate restTemplate;

	@Override
	public void login(RequestDTO<UserDTO> requestDTO, HttpServletResponse resp) {
		ResponseEntity<ResponseDTO<JwtResponseDTO>> response = restTemplate.exchange(API_LOGIN, HttpMethod.POST,
				new HttpEntity<RequestDTO<UserDTO>>(requestDTO),
				new ParameterizedTypeReference<ResponseDTO<JwtResponseDTO>>() {
				});
		ResponseDTO<JwtResponseDTO> result = response.getBody();
		if (result != null) {
			Cookie usernameCookie = new Cookie("username", result.getData().getUsername());
			usernameCookie.setPath("/admin");
			resp.addCookie(usernameCookie);
			Cookie cookie = new Cookie("token_admin", result.getData().getToken());
			cookie.setPath("/admin");
			resp.addCookie(cookie);
		}
	}

	@Override
	public void loginClient(RequestDTO<UserDTO> requestDTO, HttpServletResponse resp, Model model) {
		ResponseEntity<ResponseDTO<JwtResponseDTO>> response = restTemplate.exchange(API_LOGIN, HttpMethod.POST,
				new HttpEntity<RequestDTO<UserDTO>>(requestDTO),
				new ParameterizedTypeReference<ResponseDTO<JwtResponseDTO>>() {
				});
		ResponseDTO<JwtResponseDTO> result = response.getBody();
		if (result != null) {
			Cookie usernameCookie = new Cookie("username", result.getData().getUsername());
			Cookie tokenCookie = new Cookie("token", result.getData().getToken());
			usernameCookie.setMaxAge(24 * 60 * 60);
			usernameCookie.setPath("/");
			tokenCookie.setPath("/");
			resp.addCookie(tokenCookie);
			resp.addCookie(usernameCookie);
		}

	}
}
