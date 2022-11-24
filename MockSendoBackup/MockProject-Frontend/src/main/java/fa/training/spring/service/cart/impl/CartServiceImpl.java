package fa.training.spring.service.cart.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import fa.training.spring.dto.product.CartDTO;
import fa.training.spring.dto.product.ProductInCartDTO;
import fa.training.spring.service.cart.CartService;

@Service
public class CartServiceImpl implements CartService {

    private static final String LIST = "carts";
    private static final String OBJECT = "cart";
    private final static String API_URL = "http://localhost:8080/api/v1/";
    private final static String API_GET = API_URL + "cart/";
    private final static String API_ADD_PRODUCT_TO_CART = API_GET + "add-product-to-cart";
    private final static String API_DELETE_PRODUCT_IN_CART = API_GET + "delete-product-in-cart";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpSession httpSession;

    private ResponseDTO<CartDTO> result;

    @Override
    public void getById(Model model, RequestDTO<ProductInCartDTO> requestDTO) {
        RequestDTO<CartDTO> requestDTO1 = new RequestDTO<CartDTO>();
        String cartId = (String) httpSession.getAttribute("cartId");
        requestDTO1.setId(cartId);
        ResponseEntity<ResponseDTO<CartDTO>> response = restTemplate.exchange(
                API_GET, HttpMethod.POST, new HttpEntity<RequestDTO<CartDTO>>(requestDTO1),
                new ParameterizedTypeReference<ResponseDTO<CartDTO>>() {
                });
        result = response.getBody();
        model.addAttribute(OBJECT, result.getData());
    }

    @Override
    public String addProductToCart(ProductInCartDTO productInCartDTO) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId((String) httpSession.getAttribute("cartId"));
        List<ProductInCartDTO> productInCartDTOs = new ArrayList<>();
        productInCartDTOs.add(productInCartDTO);
        cartDTO.setProductInCarts(productInCartDTOs);
        RequestDTO<CartDTO> requestDTO = new RequestDTO<CartDTO>(cartDTO);
        ResponseEntity<ResponseDTO<CartDTO>> response = restTemplate.exchange(
                API_ADD_PRODUCT_TO_CART, HttpMethod.POST, new HttpEntity<RequestDTO<CartDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<CartDTO>>() {
                });
        result = response.getBody();
        return result.getData() != null ? "success" : "false";
    }

    @Override
    public String deleteProductInCart(RequestDTO<CartDTO> requestDTO) {
        ResponseEntity<ResponseDTO<CartDTO>> response = restTemplate.exchange(
                API_DELETE_PRODUCT_IN_CART, HttpMethod.POST, new HttpEntity<RequestDTO<CartDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<CartDTO>>() {
                });
        result = response.getBody();
        return result.getData() != null ? "success" : "false";
    }
}
