package fa.training.spring.service.product.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.product.CheckoutDTO;
import fa.training.spring.dto.user.UserDTO;
import fa.training.spring.service.product.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    RestTemplate restTemplate;

    private final static String API_URL = "http://localhost:8080/api/v1/";
    private final static String API_CHECKOUT = API_URL + "checkout";
    private final static String API_GET = API_URL + "checkout/getByUsername";
    private final static String API_CANCEL = API_URL + "checkout/cancel";
    private final static String API_DELETE = API_URL + "checkout/delete";

    private ResponseDTO<CheckoutDTO> result;

    @Override
    public boolean checkout(CheckoutDTO checkoutDTO) {
        RequestDTO<CheckoutDTO> requestDTO = new RequestDTO<CheckoutDTO>(checkoutDTO);
        ResponseEntity<ResponseDTO<CheckoutDTO>> response = restTemplate.exchange(
                API_CHECKOUT, HttpMethod.POST, new HttpEntity<RequestDTO<CheckoutDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<CheckoutDTO>>() {
                });
        result = response.getBody();
        return result != null;
    }

    @Override
    public List<CheckoutDTO> getAll(UserDTO userDTO) {
        RequestDTO<UserDTO> requestDTO = new RequestDTO<UserDTO>(userDTO);
        ResponseEntity<ResponseDTO<CheckoutDTO>> response = restTemplate.exchange(
                API_GET, HttpMethod.POST, new HttpEntity<RequestDTO<UserDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<CheckoutDTO>>() {
                });
        result = response.getBody();
        return result.getListData();
    }

    @Override
    public String cancel(CheckoutDTO checkoutDTO) {
        RequestDTO<CheckoutDTO> requestDTO = new RequestDTO<CheckoutDTO>(checkoutDTO);
        ResponseEntity<ResponseDTO<CheckoutDTO>> response = restTemplate.exchange(
                API_CANCEL, HttpMethod.POST, new HttpEntity<RequestDTO<CheckoutDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<CheckoutDTO>>() {
                });
        result = response.getBody();
        return result.getData() != null ? "Success" : "Fail";
    }

    @Override
    public String delete(CheckoutDTO checkoutDTO) {
        RequestDTO<CheckoutDTO> requestDTO = new RequestDTO<CheckoutDTO>(checkoutDTO);
        ResponseEntity<ResponseDTO<CheckoutDTO>> response = restTemplate.exchange(
                API_DELETE, HttpMethod.POST, new HttpEntity<RequestDTO<CheckoutDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<CheckoutDTO>>() {
                });
        result = response.getBody();
        return result.getData() != null ? "Success" : "Fail";
    }

}