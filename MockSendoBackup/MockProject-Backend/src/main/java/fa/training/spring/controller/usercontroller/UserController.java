package fa.training.spring.controller.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.shopdto.ShopDTO;
import fa.training.spring.dto.userdto.LocationDTO;
import fa.training.spring.dto.userdto.FormChangePassword;
import fa.training.spring.dto.userdto.UserDTO;
import fa.training.spring.service.userservice.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO<UserDTO>> getUser(@RequestBody RequestDTO<UserDTO> requestDTO) {
        UserDTO userDTO = userService.findUserById(requestDTO);
        if (userDTO == null) {
            return ResponseEntity.ok(new ResponseDTO<>("Can not found User", HttpStatus.NO_CONTENT));
        }
        return ResponseEntity.ok(new ResponseDTO<UserDTO>(userDTO, HttpStatus.OK));
    }
    
    @PostMapping("/user")
    public ResponseEntity<ResponseDTO<UserDTO>> getUserByUsername(@RequestBody RequestDTO<UserDTO> requestDTO) {
        UserDTO userDTO = userService.findByUsername(requestDTO);
        
        if (userDTO == null) {
            return ResponseEntity.ok(new ResponseDTO<>("Can not found User", HttpStatus.NO_CONTENT));
        }
        return ResponseEntity.ok(new ResponseDTO<UserDTO>(userDTO, HttpStatus.OK));
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseDTO<UserDTO>> getUsers() {
        ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setListData(userService.getUsers());
        return ResponseEntity.ok(responseDTO);
    }

    
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<UserDTO>> addUser(@RequestBody RequestDTO<UserDTO> requestDTO) {
        if (userService.findByUsername(requestDTO) == null) {
            return ResponseEntity.ok(userService.createUser(requestDTO));
        } else {
            return ResponseEntity.ok(new ResponseDTO<>(null, "User has exist", HttpStatus.NO_CONTENT));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDTO<UserDTO>> updateUser(@RequestBody RequestDTO<UserDTO> requestDTO) {
        System.out.println(requestDTO.getData());
        return ResponseEntity.ok(userService.updateUser(requestDTO));
    }

    @PostMapping("/change-password")
    public ResponseEntity<ResponseDTO<UserDTO>> changePassword(@RequestBody RequestDTO<FormChangePassword> requestDTO) {
        System.out.println("changepassword");
        System.out.println(requestDTO.getData().getIdFormChange());
        ResponseDTO<UserDTO> response = userService.changePassword(requestDTO);
        System.out.println(response.getMessage());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDTO<?>> deleteUser(@RequestBody RequestDTO<UserDTO> requestDTO) {
        return ResponseEntity.ok(new ResponseDTO<>(null, userService.deleteUser(requestDTO), HttpStatus.OK));
    }

    @PostMapping("/add-address")
    public ResponseEntity<ResponseDTO<UserDTO>> addAddress(@RequestBody RequestDTO<LocationDTO> requestDtO) {
        return ResponseEntity.ok(userService.addAddressToUser(requestDtO));
    }
    
    @PostMapping("/add-role")
    public ResponseEntity<ResponseDTO<UserDTO>> addRole(@RequestBody RequestDTO<UserDTO> requestDTO){
        
        return ResponseEntity.ok(userService.addRoleToUser(requestDTO)) ;
    }
    
   

  
}
