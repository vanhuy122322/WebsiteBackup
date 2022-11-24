package fa.training.spring.controller.client;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.product.ProductDTO;
import fa.training.spring.dto.user.UserDTO;
import fa.training.spring.service.login.LoginService;
import fa.training.spring.service.login.LogoutService;
import fa.training.spring.service.product.ProductService;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    LoginService loginService;

    @Autowired
    LogoutService logoutService;

    @GetMapping(value = { "/", "/home" })
    public String homePage(Model model, HttpServletRequest request) {
        productService.getAll(model);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if (ck.getName().equals("username")) {
                    model.addAttribute("username", ck.getValue());
                }
            }
        }
        return "pages/client/HomePage";
    }

    @PostMapping("/client/login")
    public String loginClient(UserDTO userDTO, HttpServletResponse response, Model model) {
        RequestDTO<UserDTO> requestDTO = new RequestDTO<UserDTO>(userDTO);
        loginService.loginClient(requestDTO, response, model);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logoutClient(HttpServletRequest request, HttpServletResponse response) {
        logoutService.logoutClient(request, response);
        return "redirect:/home";
    }

    @PostMapping("/search")
    @ResponseBody
    public List<ProductDTO> searchProduct(ProductDTO productDTO) {
        RequestDTO<ProductDTO> requestDTO = new RequestDTO<ProductDTO>(productDTO);
        return productService.search(requestDTO);
    }
}
