package fa.training.spring.service.user.impl;

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
import fa.training.spring.dto.user.FormChangePassword;
import fa.training.spring.dto.user.UserDTO;
import fa.training.spring.service.GetHeaders;
import fa.training.spring.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final static String API_URL = "http://localhost:8080/api/v1/user";
	private final static String API_GET = API_URL + "/";
	private final static String API_GET_BY_USERNAME = API_URL + "/user";
	private final static String API_LIST = API_URL + "/users";
	private final static String API_ADD = API_URL + "/add";
	private final static String API_DELETE = API_URL + "/delete";
	private final static String API_UPDATE = API_URL + "/update";
	private final static String API_CHANGEPASS = API_URL + "/change-password";
	private final static String API_ADD_ROLE = API_URL + "/add-role";
	private final static String API_REGISTER_USER = "http://localhost:8080/api/v1/signup";

	private ResponseDTO<UserDTO> result;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	GetHeaders getHeaders;

	@Override
	public void getUser(String id, Model model) {
		RequestDTO<UserDTO> requestDTO = new RequestDTO<UserDTO>();
		requestDTO.setId(id);
		HttpEntity<RequestDTO<UserDTO>> request = new HttpEntity<RequestDTO<UserDTO>>(requestDTO,
				getHeaders.addHeadersAdmin());
		ResponseEntity<ResponseDTO<UserDTO>> response = restTemplate.exchange(API_GET, HttpMethod.POST, request,
				new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {
				});
		result = response.getBody();
		System.out.println(result.getData());
		model.addAttribute("user", result.getData());
	}

	@Override
	public void getUsers(Model model) {
		System.out.println(getHeaders.addHeaders());
		ResponseEntity<ResponseDTO<UserDTO>> response = restTemplate.exchange(API_LIST, HttpMethod.GET,
				new HttpEntity<>(getHeaders.addHeadersAdmin()), new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {
				});
		result = response.getBody();
		System.out.println(result.getListData().get(0));
		model.addAttribute("users", result.getListData());
	}

	@Override
	public void add(RequestDTO<UserDTO> requestDTO) {
		ResponseEntity<ResponseDTO<UserDTO>> response = restTemplate.exchange(API_ADD, HttpMethod.POST,
				new HttpEntity<RequestDTO<UserDTO>>(requestDTO),
				new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {
				});
		result = response.getBody();
	}

	@Override
	public void delete(String id) {
		RequestDTO<UserDTO> requestDTO = new RequestDTO<>();
		requestDTO.setId(id);
		ResponseEntity<ResponseDTO<UserDTO>> response = restTemplate.exchange(API_DELETE, HttpMethod.POST,
				new HttpEntity<RequestDTO<UserDTO>>(requestDTO, getHeaders.addHeadersAdmin()),
				new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {
				});
		result = response.getBody();
	}

	@Override
	public void update(RequestDTO<UserDTO> requestDTO) {
		ResponseEntity<ResponseDTO<UserDTO>> response = restTemplate.exchange(API_UPDATE, HttpMethod.POST,
				new HttpEntity<RequestDTO<UserDTO>>(requestDTO, getHeaders.addHeadersAdmin()),
				new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {
				});
		result = response.getBody();
	}

	@Override
	public ResponseDTO<UserDTO> changePassword(RequestDTO<FormChangePassword> requestDTO) {
		ResponseEntity<ResponseDTO<UserDTO>> response = restTemplate.exchange(API_CHANGEPASS,
				HttpMethod.POST,
				new HttpEntity<RequestDTO<FormChangePassword>>(requestDTO, getHeaders.addHeadersAdmin()),
				new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {
				});
		result = response.getBody();
		return result;
	}

	@Override
	public ResponseDTO<UserDTO> changePasswordCustomer(RequestDTO<FormChangePassword> requestDTO) {
		ResponseEntity<ResponseDTO<UserDTO>> response = restTemplate.exchange(API_CHANGEPASS,
				HttpMethod.POST,
				new HttpEntity<RequestDTO<FormChangePassword>>(requestDTO, getHeaders.addHeaders()),
				new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {
				});
		result = response.getBody();
		return result;
	}

	@Override
	public ResponseDTO<UserDTO> getUserByUsername(HttpServletRequest request, Model model) {
		String username = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username")) {
				username = cookie.getValue();
			}
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		RequestDTO<UserDTO> requestDTO = new RequestDTO<UserDTO>(userDTO);
		HttpEntity<RequestDTO<UserDTO>> requestEntity = new HttpEntity<RequestDTO<UserDTO>>(requestDTO,
				getHeaders.addHeaders());
		ResponseEntity<ResponseDTO<UserDTO>> response = restTemplate.exchange(API_GET_BY_USERNAME, HttpMethod.POST,
				requestEntity,
				new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {
				});
		result = response.getBody();
		model.addAttribute("user", result.getData());
		model.addAttribute("username", username);
		return result;

	}

	@Override
	public ResponseDTO<UserDTO> updateCustomer(RequestDTO<UserDTO> requestDTO) {
		ResponseEntity<ResponseDTO<UserDTO>> response = restTemplate.exchange(API_UPDATE, HttpMethod.POST,
				new HttpEntity<RequestDTO<UserDTO>>(requestDTO, getHeaders.addHeaders()),
				new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {
				});
		result = response.getBody();
		return result;
	}

	@Override
	public ResponseDTO<UserDTO> addRoleToUser(RequestDTO<UserDTO> requestDTO) {

		ResponseEntity<ResponseDTO<UserDTO>> response = restTemplate.exchange(API_ADD_ROLE, HttpMethod.POST,
				new HttpEntity<RequestDTO<UserDTO>>(requestDTO, getHeaders.addHeadersAdmin()),
				new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {
				});
		result = response.getBody();
		return result;
	}

	@Override
	public ResponseDTO<UserDTO> registerUser(RequestDTO<UserDTO> requestDTO) {
		ResponseEntity<ResponseDTO<UserDTO>> response = restTemplate.exchange(API_REGISTER_USER, HttpMethod.POST,
				new HttpEntity<RequestDTO<UserDTO>>(requestDTO),
				new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {
				});
		System.out.println(response.getBody().getMessage());
		return response.getBody();
	}

}
