package fa.training.spring.service.user.impl;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
import fa.training.spring.dto.user.UserDTO;
import fa.training.spring.service.GetHeaders;
import fa.training.spring.service.user.LocationService;

@Service
public class LocationServiceImpl implements LocationService{
	
	private final static String API_URL = "http://localhost:8080/api/v1/user/location";
	private final static String API_GETLIST = API_URL + "/";
	
	@Autowired
	GetHeaders getHeaders;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public void getLocations(HttpServletRequest request, Model model) {
		String username = "";
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("username")) {
				username = cookie.getValue();
			}
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		RequestDTO<UserDTO> requestDTO = new RequestDTO<UserDTO>(userDTO);
		ResponseEntity<ResponseDTO<UserDTO>> response = restTemplate.exchange(API_GETLIST, HttpMethod.GET,
				new HttpEntity<>(requestDTO,getHeaders.addHeaders()), new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {
				});
		ResponseDTO<UserDTO> result = response.getBody();
		model.addAttribute("locations", result.getListData());
	}

	
	
}
