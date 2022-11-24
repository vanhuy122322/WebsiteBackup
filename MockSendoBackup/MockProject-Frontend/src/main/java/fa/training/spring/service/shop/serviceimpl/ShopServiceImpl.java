package fa.training.spring.service.shop.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

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
import fa.training.spring.dto.product.CategoryDTO;
import fa.training.spring.dto.product.CheckoutDTO;
import fa.training.spring.dto.product.ProductDTO;
import fa.training.spring.dto.shop.ShopDTO;
import fa.training.spring.dto.user.UserDTO;
import fa.training.spring.service.shop.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	private static final String LIST = "shops";
	private static final String OBJECT = "shop";
	private static final String LISTCATEGORY = "categories";
	private static final String API_URL = "http://localhost:8080/api/v1/";
	private final static String API_CREATE_PROUCT = API_URL + "product/add";
	private final static String API_GET = API_URL + "shop/";
	private final static String API_CREATE = API_GET + "add";
	private final static String API_UPDATE = API_GET + "update";
	private final static String API_DELETE = API_GET + "delete";
	private final static String API_GET_SLUG = API_GET + "findByName";
	private final static String API_GET_PRODUCT_BY_NAME = "http://localhost:8080/api/v1/product/"
			+ "findProductByShopName";
	private final static String API_GET_CHECKOUT_BY_NAME = API_GET + "findCheckoutByShopName";
	private final static String API_UPDATE_STATUS_CHECKOUT = API_GET + "updateStatusCheckout";
	private final static String API_CHECKOUT = API_GET + "findByCheckoutCode";

	@Autowired
	RestTemplate restTemplate;
	private ResponseDTO<ShopDTO> result;

	private ResponseDTO<ProductDTO> resultProduct;

	private ResponseDTO<CategoryDTO> resultCategory;

	@Override
	public void add(RequestDTO<ShopDTO> requestDTO) {
		ResponseEntity<ResponseDTO<ShopDTO>> response = restTemplate.exchange(API_CREATE, HttpMethod.POST,
				new HttpEntity<RequestDTO<ShopDTO>>(requestDTO),
				new ParameterizedTypeReference<ResponseDTO<ShopDTO>>() {
				});
		result = response.getBody();
	}

	@Override
	public void getOneByName(String name, Model model) {
		RequestDTO<UserDTO> requestDTO = new RequestDTO<UserDTO>();
		UserDTO userDTO = new UserDTO();
		userDTO.setShopName(name);
		requestDTO.setData(userDTO);
		HttpEntity<RequestDTO<UserDTO>> request = new HttpEntity<RequestDTO<UserDTO>>(requestDTO);
		ResponseEntity<ResponseDTO<ShopDTO>> response = restTemplate.exchange(API_GET_SLUG, HttpMethod.POST, request,
				new ParameterizedTypeReference<ResponseDTO<ShopDTO>>() {
				});
		ResponseDTO<ShopDTO> result = response.getBody();

		RequestDTO<ShopDTO> requestDTO1 = new RequestDTO<ShopDTO>();
		ShopDTO ShopDTO1 = new ShopDTO();
		ShopDTO1.setName(name);
		requestDTO1.setData(ShopDTO1);
		HttpEntity<RequestDTO<ShopDTO>> requestShop = new HttpEntity<RequestDTO<ShopDTO>>(requestDTO1);
		model.addAttribute("daysale",
				LocalDateTime.now().getDayOfYear() - result.getData().getCreatedDate().getDayOfYear());
		model.addAttribute(OBJECT, result.getData());
	}

	@Override
	public void getProductByName(String name, Model model) {
		RequestDTO<ShopDTO> requestDTO = new RequestDTO<ShopDTO>();
		ShopDTO ShopDTO = new ShopDTO();
		ShopDTO.setName(name);
		requestDTO.setData(ShopDTO);
		HttpEntity<RequestDTO<ShopDTO>> request = new HttpEntity<RequestDTO<ShopDTO>>(requestDTO);
		ResponseEntity<ResponseDTO<ProductDTO>> response = restTemplate.exchange(API_GET_PRODUCT_BY_NAME,
				HttpMethod.POST, request,
				new ParameterizedTypeReference<ResponseDTO<ProductDTO>>() {
				});
		ResponseEntity<ResponseDTO<CategoryDTO>> responseCategory = restTemplate.exchange(API_URL + "category",
				HttpMethod.GET, null, new ParameterizedTypeReference<ResponseDTO<CategoryDTO>>() {
				});
		ResponseDTO<ProductDTO> resultProuct = response.getBody();
		model.addAttribute("products", resultProuct.getListData());
		for (ProductDTO product : resultProuct.getListData()) {
			int i = 1;
			i++;
			model.addAttribute("amountproduct", i);
		}
		resultCategory = responseCategory.getBody();
		model.addAttribute(LISTCATEGORY, resultCategory.getListData());
	}

	@Override
	public List<ProductDTO> getProductByShopName(String name) {
		RequestDTO<ShopDTO> requestDTO = new RequestDTO<ShopDTO>();
		ShopDTO ShopDTO = new ShopDTO();
		ShopDTO.setName(name);
		requestDTO.setData(ShopDTO);
		HttpEntity<RequestDTO<ShopDTO>> request = new HttpEntity<RequestDTO<ShopDTO>>(requestDTO);
		ResponseEntity<ResponseDTO<ProductDTO>> response = restTemplate.exchange(API_GET_PRODUCT_BY_NAME,
				HttpMethod.POST, request,
				new ParameterizedTypeReference<ResponseDTO<ProductDTO>>() {
				});
		ResponseDTO<ProductDTO> resultProuct = response.getBody();
		return resultProuct.getListData();
	}

	@Override
	public void getCheckoutByShopName(String shopName,Model model) {
		RequestDTO<ShopDTO> requestDTO = new RequestDTO<ShopDTO>();
		ShopDTO ShopDTO = new ShopDTO();
		ShopDTO.setName(shopName);
		requestDTO.setData(ShopDTO);
		HttpEntity<RequestDTO<ShopDTO>> request = new HttpEntity<RequestDTO<ShopDTO>>(requestDTO);
		ResponseEntity<ResponseDTO<CheckoutDTO>> response = restTemplate.exchange(API_GET_CHECKOUT_BY_NAME, HttpMethod.POST, request,
				new ParameterizedTypeReference<ResponseDTO<CheckoutDTO>>() {
				});
		ResponseDTO<CheckoutDTO> resultCheckout = response.getBody();
		model.addAttribute("checkout", resultCheckout.getListData());
	}

	@Override
	public String updateStatusCheckout(CheckoutDTO checkoutDTO) {
		System.out.println(checkoutDTO);
		 RequestDTO<CheckoutDTO> requestDTO = new RequestDTO<CheckoutDTO>(checkoutDTO);
	        ResponseEntity<ResponseDTO<CheckoutDTO>> response = restTemplate.exchange(
	        		API_UPDATE_STATUS_CHECKOUT, HttpMethod.POST, new HttpEntity<RequestDTO<CheckoutDTO>>(requestDTO),
	                new ParameterizedTypeReference<ResponseDTO<CheckoutDTO>>() {
	                });
	        ResponseDTO<CheckoutDTO> resultCheckout = response.getBody();
	        return result.getData() != null ? "Success" : "Fail";
	}

	@Override
	public void getCheckoutByCheckoutCode(String checkoutCode,Model model) {
		System.out.println(checkoutCode);
		RequestDTO<CheckoutDTO> requestDTO = new RequestDTO<CheckoutDTO>();
		CheckoutDTO CheckoutDTO = new CheckoutDTO();
		CheckoutDTO.setCheckoutCode(checkoutCode);
		requestDTO.setData(CheckoutDTO);
		HttpEntity<RequestDTO<CheckoutDTO>> request = new HttpEntity<RequestDTO<CheckoutDTO>>(requestDTO);
	        ResponseEntity<ResponseDTO<CheckoutDTO>> response = restTemplate.exchange(
	        		API_CHECKOUT, HttpMethod.POST, new HttpEntity<RequestDTO<CheckoutDTO>>(requestDTO),
	                new ParameterizedTypeReference<ResponseDTO<CheckoutDTO>>() {
	                });
	        ResponseDTO<CheckoutDTO> resultCheckout = response.getBody();
	        System.out.println(resultCheckout.getData());
	        model.addAttribute("checkout", resultCheckout.getData());
	       
	}

}
