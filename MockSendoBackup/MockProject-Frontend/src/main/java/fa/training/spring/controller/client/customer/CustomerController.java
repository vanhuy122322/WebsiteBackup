package fa.training.spring.controller.client.customer;

import javax.servlet.http.HttpServletRequest;

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
import fa.training.spring.dto.product.CheckoutDTO;
import fa.training.spring.dto.user.FormChangePassword;
import fa.training.spring.dto.user.UserDTO;
import fa.training.spring.service.product.CheckoutService;
import fa.training.spring.service.user.LocationService;
import fa.training.spring.service.user.UserService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	UserService userService;

	@Autowired
	CheckoutService checkoutService;

	@Autowired
	LocationService locationService;

	@GetMapping
	public String getCustomer(Model model, HttpServletRequest request, String message) {
		userService.getUserByUsername(request, model);
		if (message != null) {
			model.addAttribute("message", message);
		}
		return "pages/client/customer/Customer";
	}

	@PostMapping("/update")
	public RedirectView updateCustomer(UserDTO userDTO, RedirectAttributes redirectAttributes) {
		RequestDTO<UserDTO> requestDTO = new RequestDTO<UserDTO>(userDTO);
		ResponseDTO<UserDTO> responseDTO = userService.updateCustomer(requestDTO);
		String message = responseDTO.getMessage();
		if (message != null) {
			redirectAttributes.addAttribute("message", message);
		}
		return new RedirectView("/customer");
	}

	@GetMapping("/location")
	public String location(Model model, HttpServletRequest request) {
		return "pages/client/customer/Location";
	}

	@GetMapping("/order/wait-accept")
	public String OrderWaitAccept(Model model, HttpServletRequest request) {
		UserDTO userDTO = (UserDTO) request.getAttribute("user");
		model.addAttribute("orders", checkoutService.getAll(userDTO));
		return "pages/client/customer/order/WaitAccept";
	}

	@GetMapping("/order/has-accept")
	public String OrderHasAccept(Model model, HttpServletRequest request) {
		UserDTO userDTO = (UserDTO) request.getAttribute("user");
		model.addAttribute("orders", checkoutService.getAll(userDTO));
		return "pages/client/customer/order/HasAccept";
	}

	@GetMapping("/order/delivery")
	public String OrderDelivery(Model model, HttpServletRequest request) {
		UserDTO userDTO = (UserDTO) request.getAttribute("user");
		model.addAttribute("orders", checkoutService.getAll(userDTO));
		return "pages/client/customer/order/Delivery";
	}

	@GetMapping("/order/completed")
	public String OrderCompleted(Model model, HttpServletRequest request) {
		UserDTO userDTO = (UserDTO) request.getAttribute("user");
		model.addAttribute("orders", checkoutService.getAll(userDTO));
		return "pages/client/customer/order/Completed";
	}

	@GetMapping("/order/has-cancel")
	public String OrderHasCancel(Model model, HttpServletRequest request) {
		UserDTO userDTO = (UserDTO) request.getAttribute("user");
		model.addAttribute("orders", checkoutService.getAll(userDTO));
		return "pages/client/customer/order/HasCancel";
	}

	@PostMapping("/order/cancel-order")
	@ResponseBody
	public String CancelOrder(CheckoutDTO checkoutDTO) {
		return checkoutService.cancel(checkoutDTO);
	}

	@PostMapping("/order/delete-order")
	@ResponseBody
	public String DeleteOrder(CheckoutDTO checkoutDTO) {
		return checkoutService.delete(checkoutDTO);
	}

	@PostMapping("/change-password")
	public RedirectView changePassCustomer(FormChangePassword formChangePassword, RedirectAttributes attribute) {
		System.out.println(formChangePassword);
		ResponseDTO<UserDTO> response = userService
				.changePasswordCustomer(new RequestDTO<FormChangePassword>(formChangePassword));
		if (response.getMessage().equals("success")) {
			attribute.addAttribute("message", "Change password success");
			return new RedirectView("/customer");
		} else {
			attribute.addAttribute("message", "Change Password fail");
			return new RedirectView("/customer");
		}
	}
}
