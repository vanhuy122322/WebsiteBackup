package fa.training.spring.controller.secutirycontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.userdto.JwtResponse;
import fa.training.spring.dto.userdto.UserDTO;
import fa.training.spring.service.userservice.RoleService;
import fa.training.spring.service.userservice.UserService;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@PostMapping("/signup")
	public ResponseEntity<?> register(@Valid @RequestBody RequestDTO<UserDTO> requestDTO) {
		return new ResponseEntity<>(userService.createUser(requestDTO), HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<ResponseDTO<JwtResponse>> login(@RequestBody RequestDTO<UserDTO> requestDTO) {
	    System.out.println(requestDTO.getData().getUsername());
		return ResponseEntity.ok(userService.login(requestDTO));
	}

}
