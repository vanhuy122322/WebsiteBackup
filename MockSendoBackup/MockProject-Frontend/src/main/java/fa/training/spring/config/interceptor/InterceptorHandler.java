package fa.training.spring.config.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.product.CartDTO;
import fa.training.spring.dto.user.UserDTO;
import fa.training.spring.service.GetHeaders;

@Component
public class InterceptorHandler implements HandlerInterceptor {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GetHeaders getHeaders;

    @Autowired
    HttpSession httpSession;

    String username = "";
    int status = 200;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("username")) {
                    this.username = cookies[i].getValue();
                    if (this.username.isEmpty()) {

                    } else {
                        UserDTO userDTO = new UserDTO();
                        userDTO.setUsername(this.username);
                        RequestDTO<UserDTO> requestDTO = new RequestDTO<UserDTO>(userDTO);
                        HttpEntity<RequestDTO<UserDTO>> requestEntity = new HttpEntity<RequestDTO<UserDTO>>(requestDTO,
                                getHeaders.addHeaders());
                        ResponseEntity<ResponseDTO<UserDTO>> resp = restTemplate.exchange(
                                "http://localhost:8080/api/v1/user/user",
                                HttpMethod.POST,
                                requestEntity,
                                new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {
                                });
                        ResponseDTO<UserDTO> result = resp.getBody();
                        request.setAttribute("user", result.getData());

                        try {
                            CartDTO cartDTO = new CartDTO();
                            cartDTO.setUsername(this.username);
                            RequestDTO<CartDTO> requestDTOCart = new RequestDTO<CartDTO>(cartDTO);
                            HttpEntity<RequestDTO<CartDTO>> requestEntityCart = new HttpEntity<RequestDTO<CartDTO>>(
                                    requestDTOCart);
                            ResponseEntity<ResponseDTO<CartDTO>> respCart = restTemplate.exchange(
                                    "http://localhost:8080/api/v1/cart/get-by-username",
                                    HttpMethod.POST,
                                    requestEntityCart,
                                    new ParameterizedTypeReference<ResponseDTO<CartDTO>>() {
                                    });
                            ResponseDTO<CartDTO> resultCart = respCart.getBody();
                            httpSession.setAttribute("cartId", resultCart.getData().getId());
                        } catch (Exception e) {

                        }
                    }
                }
            }
        }

        if (this.username.isEmpty() && uri.contains("buy-now")) {
            this.status = 403;
            response.setStatus(status);
        } else if (uri.equals("/error") && uri.contains("buy-now")) {
            this.status = 404;
            response.setStatus(status);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (this.status == 403) {
            modelAndView.setViewName("/pages/client/ForbiddenPage");
        } else if (this.status == 404) {
            modelAndView.setViewName("/pages/client/NotFound");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception exception) throws Exception {

    }
}
