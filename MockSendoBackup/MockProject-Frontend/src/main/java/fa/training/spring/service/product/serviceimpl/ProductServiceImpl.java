package fa.training.spring.service.product.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import fa.training.spring.dto.product.ProductDTO;
import fa.training.spring.dto.shop.ShopDTO;
import fa.training.spring.dto.user.UserDTO;
import fa.training.spring.service.product.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final String LIST = "products";
	private static final String LISTCATEGORY = "categories";
	private static final String OBJECT = "product";
	private static final String API_URL = "http://localhost:8080/api/v1/";
	private final static String API_GET = API_URL + "product/";
	private final static String API_CREATE = API_GET + "add";
	private final static String API_UPDATE = API_GET + "update";
	private final static String API_DELETE = API_GET + "delete";
	private final static String API_SAVE_LIST_EXCEL = API_GET + "add-list";
	private final static String API_GET_SLUG = API_GET + "findBySlug";
	private final static String API_SEARCH = API_GET + "search";
	private final static String API_GET_NAME_OR_CATEGORY = API_GET + "findByCategoryOrSubCategory";
	private final static String API_GET_PRODUCT_DETAIL = API_GET + "findByProductDetail";
	private final static String API_GET_SUB_CATEGORY = "http://localhost:8080/api/v1/category/" + "findBySubCategory";
	private final static String API_GET_CATEGORY = "http://localhost:8080/api/v1/category/" + "findCategory";
	@Autowired
	RestTemplate restTemplate;
	private ResponseDTO<ProductDTO> result;

	private ResponseDTO<CategoryDTO> resultCategory;

	public void getAll(Model model) {
		ResponseEntity<ResponseDTO<ProductDTO>> responseProduct = restTemplate.exchange(API_GET, HttpMethod.GET, null,
				new ParameterizedTypeReference<ResponseDTO<ProductDTO>>() {
				});
		ResponseEntity<ResponseDTO<CategoryDTO>> responseCategory = restTemplate.exchange(API_URL + "category",
				HttpMethod.GET, null, new ParameterizedTypeReference<ResponseDTO<CategoryDTO>>() {
				});
		result = responseProduct.getBody();
		model.addAttribute(LIST, result.getListData());
		resultCategory = responseCategory.getBody();
		model.addAttribute(LISTCATEGORY, resultCategory.getListData());
	}

	@Override
	public void add(RequestDTO<ProductDTO> requestDTO) {
		ResponseEntity<ResponseDTO<ProductDTO>> response = restTemplate.exchange(API_CREATE, HttpMethod.POST,
				new HttpEntity<RequestDTO<ProductDTO>>(requestDTO),
				new ParameterizedTypeReference<ResponseDTO<ProductDTO>>() {
				});
		result = response.getBody();
	}

	@Override
	public void updatePage(String id, Model model) {
		RequestDTO<ProductDTO> requestDTO = new RequestDTO<>();
		requestDTO.setId(id);
		ResponseEntity<ResponseDTO<ProductDTO>> response = restTemplate.exchange(API_GET, HttpMethod.POST,
				new HttpEntity<RequestDTO<ProductDTO>>(requestDTO),
				new ParameterizedTypeReference<ResponseDTO<ProductDTO>>() {
				});
		result = response.getBody();
		model.addAttribute(OBJECT, result.getData());
		ResponseEntity<ResponseDTO<CategoryDTO>> responseCategory = restTemplate.exchange(API_URL + "category",
				HttpMethod.GET, null, new ParameterizedTypeReference<ResponseDTO<CategoryDTO>>() {
				});
		resultCategory = responseCategory.getBody();
		model.addAttribute(LISTCATEGORY, resultCategory.getListData());
	}

	public void update(RequestDTO<ProductDTO> requestDTO) {
		ResponseEntity<ResponseDTO<ProductDTO>> response = restTemplate.exchange(API_UPDATE, HttpMethod.POST,
				new HttpEntity<RequestDTO<ProductDTO>>(requestDTO),
				new ParameterizedTypeReference<ResponseDTO<ProductDTO>>() {
				});
		result = response.getBody();
	}

	@Override
	public void delete(String id) {
		RequestDTO<ProductDTO> requestDTO = new RequestDTO<>();
		requestDTO.setId(id);
		ResponseEntity<ResponseDTO<ProductDTO>> response = restTemplate.exchange(API_DELETE, HttpMethod.POST,
				new HttpEntity<RequestDTO<ProductDTO>>(requestDTO),
				new ParameterizedTypeReference<ResponseDTO<ProductDTO>>() {
				});
		result = response.getBody();
	}

	@Override
	public void getOneBySlug(String slug, Model model) {
		RequestDTO<ProductDTO> requestDTO = new RequestDTO<ProductDTO>();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setSlug(slug);
		requestDTO.setData(productDTO);
		HttpEntity<RequestDTO<ProductDTO>> request = new HttpEntity<RequestDTO<ProductDTO>>(requestDTO);
		ResponseEntity<ResponseDTO<ProductDTO>> response = restTemplate.exchange(API_GET_SLUG, HttpMethod.POST, request,
				new ParameterizedTypeReference<ResponseDTO<ProductDTO>>() {
				});
		ResponseDTO<ProductDTO> result = response.getBody();
		model.addAttribute("product", result.getData());

		String shopName = result.getData().getShopName();
		RequestDTO<UserDTO> requestUserDTO = new RequestDTO<UserDTO>();
		UserDTO userDTO = new UserDTO();
		userDTO.setShopName(shopName);
		requestUserDTO.setData(userDTO);
		HttpEntity<RequestDTO<UserDTO>> requestUser = new HttpEntity<RequestDTO<UserDTO>>(requestUserDTO);
		ResponseEntity<ResponseDTO<ShopDTO>> responseShopDTO = restTemplate.exchange(
				"http://localhost:8080/api/v1/shop/findByName", HttpMethod.POST,
				requestUser,
				new ParameterizedTypeReference<ResponseDTO<ShopDTO>>() {
				});
		ResponseDTO<ShopDTO> resultShopDTO = responseShopDTO.getBody();
		model.addAttribute("shop", resultShopDTO.getData());
	}

	@Override
	public void getProductByCategoryName(String name, Model model) {
		RequestDTO<ProductDTO> requestDTO = new RequestDTO<ProductDTO>();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setCategory(name);
		requestDTO.setData(productDTO);
		System.out.println(name);
		String category = name;
		HttpEntity<RequestDTO<ProductDTO>> request = new HttpEntity<RequestDTO<ProductDTO>>(requestDTO);
		ResponseEntity<ResponseDTO<ProductDTO>> response = restTemplate.exchange(API_GET_NAME_OR_CATEGORY,
				HttpMethod.POST, request, new ParameterizedTypeReference<ResponseDTO<ProductDTO>>() {
				});
		ResponseEntity<ResponseDTO<CategoryDTO>> response1 = restTemplate.exchange(API_GET_CATEGORY,
				HttpMethod.POST, request, new ParameterizedTypeReference<ResponseDTO<CategoryDTO>>() {
				});
		ResponseDTO<ProductDTO> result = response.getBody();
		ResponseDTO<CategoryDTO> result1 = response1.getBody();
		model.addAttribute("category", result1.getData());
		System.out.println(result1.getData());
		if (result.getListData() != null) {
			model.addAttribute(LIST, result.getListData());
			List<String> sizes = new ArrayList<>();
			for (ProductDTO productDTO2 : result.getListData()) {
				sizes.add(productDTO2.getProductDetail().getSize());
			}
			List<String> listSize = sizes.stream().distinct().collect(Collectors.toList());
			model.addAttribute("sizes", listSize);
			List<String> origins = new ArrayList<>();
			for (ProductDTO productDTO2 : result.getListData()) {
				origins.add(productDTO2.getProductDetail().getOrigin());
			}
			List<String> listOrigin = origins.stream().distinct().collect(Collectors.toList());
			model.addAttribute("origins", listOrigin);
			List<String> materials = new ArrayList<>();
			for (ProductDTO productDTO2 : result.getListData()) {
				materials.add(productDTO2.getProductDetail().getMaterial());
			}
			List<String> listMaterial = materials.stream().distinct().collect(Collectors.toList());
			model.addAttribute("materials", listMaterial);
		}
		model.addAttribute("subcategory", category);
	}

	@Override
	public void getProductBySubCategoryName(String subcategory, Model model) {
		RequestDTO<ProductDTO> requestDTO = new RequestDTO<ProductDTO>();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setSubCategory(subcategory);
		requestDTO.setData(productDTO);
		String name = subcategory;
		HttpEntity<RequestDTO<ProductDTO>> request = new HttpEntity<RequestDTO<ProductDTO>>(requestDTO);
		ResponseEntity<ResponseDTO<ProductDTO>> response = restTemplate.exchange(API_GET_NAME_OR_CATEGORY,
				HttpMethod.POST, request, new ParameterizedTypeReference<ResponseDTO<ProductDTO>>() {
				});
		ResponseEntity<ResponseDTO<CategoryDTO>> response1 = restTemplate.exchange(API_GET_SUB_CATEGORY,
				HttpMethod.POST, request, new ParameterizedTypeReference<ResponseDTO<CategoryDTO>>() {
				});
		ResponseDTO<ProductDTO> result = response.getBody();
		ResponseDTO<CategoryDTO> result1 = response1.getBody();

		model.addAttribute("category", result1.getData());
		if (result.getListData() != null) {
			model.addAttribute(LIST, result.getListData());
			List<String> sizes = new ArrayList<>();
			for (ProductDTO productDTO2 : result.getListData()) {
				sizes.add(productDTO2.getProductDetail().getSize());
			}
			List<String> listSize = sizes.stream().distinct().collect(Collectors.toList());
			model.addAttribute("sizes", listSize);
			List<String> origins = new ArrayList<>();
			for (ProductDTO productDTO2 : result.getListData()) {
				origins.add(productDTO2.getProductDetail().getOrigin());
			}
			List<String> listOrigin = origins.stream().distinct().collect(Collectors.toList());
			model.addAttribute("origins", listOrigin);
			List<String> materials = new ArrayList<>();
			for (ProductDTO productDTO2 : result.getListData()) {
				materials.add(productDTO2.getProductDetail().getMaterial());
			}
			List<String> listMaterial = materials.stream().distinct().collect(Collectors.toList());
			model.addAttribute("materials", listMaterial);
		}
		model.addAttribute("subcategory", subcategory);
	}

	@Override
	public ResponseDTO<ProductDTO> getProductByProductDetail(RequestDTO<ProductDTO> requestDTO) {
		System.out.println(requestDTO);
		HttpEntity<RequestDTO<ProductDTO>> request = new HttpEntity<>(requestDTO);
		ResponseEntity<ResponseDTO<ProductDTO>> response = restTemplate.exchange(API_GET_PRODUCT_DETAIL,
				HttpMethod.POST, request, new ParameterizedTypeReference<ResponseDTO<ProductDTO>>() {
				});
		ResponseDTO<ProductDTO> result = response.getBody();
		System.out.println(result.getListData());
		return result;

	}

	@Override
	public List<ProductDTO> search(RequestDTO<ProductDTO> requestDTO) {
		HttpEntity<RequestDTO<ProductDTO>> request = new HttpEntity<RequestDTO<ProductDTO>>(requestDTO);
		ResponseEntity<ResponseDTO<ProductDTO>> response = restTemplate.exchange(API_SEARCH, HttpMethod.POST,
				request,
				new ParameterizedTypeReference<ResponseDTO<ProductDTO>>() {
				});
		result = response.getBody();
		return result.getListData();
	}

	@Override
	public void sendDataFromExcelToAPIAndSave(RequestDTO<ProductDTO> requestDTO) {
		HttpEntity<RequestDTO<ProductDTO>> request = new HttpEntity<>(requestDTO);
		ResponseEntity<ResponseDTO<ProductDTO>> response = restTemplate.exchange(API_SAVE_LIST_EXCEL, HttpMethod.POST,
				request, new ParameterizedTypeReference<ResponseDTO<ProductDTO>>() {
				});
		ResponseDTO<ProductDTO> result = response.getBody();

		if (result.getStatus().name().equals("CREATED")) {
			System.out.println("Success");
		} else {
			System.out.println("Fail");
		}
	}
}
