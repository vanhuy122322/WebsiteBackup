package fa.training.spring.controller.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.user.FormChangePassword;
import fa.training.spring.dto.user.UserDTO;
import fa.training.spring.service.user.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public String getUsers(Model model) {
        userService.getUsers(model);
        return "pages/admin/user/ViewUser";
    }

    @PostMapping(value = "/add")
    public String add(UserDTO userDTO) {
        RequestDTO<UserDTO> requestDTO = new RequestDTO<UserDTO>(userDTO);
        userService.add(requestDTO);
        return "redirect:/admin/user";
    }

    @GetMapping("/update")
    public String update(String id, Model model, String message) {
        userService.getUser(id, model);
        if(message != null) {
        	model.addAttribute("message", message);
        }
        return "pages/admin/user/UpdateUser";
    }

    @PostMapping("/update")
    public String update(UserDTO userDTO) {
        System.out.println(userDTO);
        RequestDTO<UserDTO> requestDTO = new RequestDTO<UserDTO>(userDTO);
        userService.update(requestDTO);
        return "redirect:/admin/user/update?id=" + userDTO.getId();
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(String id){
    	System.out.println(id);
        userService.delete(id);
        return "Delete Success";
    }
    
    @PostMapping("/change-password")
    public RedirectView changePassword(FormChangePassword form, RedirectAttributes attribute) {
    	RequestDTO<FormChangePassword> requestDTO = new RequestDTO<FormChangePassword>(form);
    	ResponseDTO<UserDTO> response = userService.changePassword(requestDTO);
    	if(response.getMessage().equals("success")) {
    		attribute.addAttribute("message", "Change password success");
    		return new RedirectView("/admin/user/update?id=" + response.getData().getId()) ;
    	} else {
    		attribute.addAttribute("message", "Change Password fail");
    		return new RedirectView("/admin/user/update?id=" + response.getData().getId());
    	}
    }
    
    @PostMapping("/add-role")
    public String addRoleToUser(UserDTO userDTO) {
		RequestDTO<UserDTO> requestDTO = new RequestDTO<UserDTO>();
		requestDTO.setData(userDTO);
		ResponseDTO<UserDTO> responseDTO = userService.addRoleToUser(requestDTO);
    	return "redirect:/admin/user";
    	
    }
    
    
}
