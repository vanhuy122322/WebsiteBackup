package fa.training.spring.service.product.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.product.SubCategoryDTO;
import fa.training.spring.service.product.SubCategoryService;

public class SubCategoryServiceImpl implements SubCategoryService {

    private static final String LIST = "subCategories";
    private static final String OBJECT = "subCategory";
    private final static String API_URL = "http://localhost:8080/api/v1/";
    private final static String API_GET = API_URL + "subcategory/";
    private final static String API_CREATE = API_GET + "add";
    private final static String API_UPDATE = API_GET + "update";
    private final static String API_DELETE = API_GET + "delete";

    @Autowired
    RestTemplate restTemplate;

    private ResponseDTO<SubCategoryDTO> result;

    public void getAll(Model model) {
        ResponseEntity<ResponseDTO<SubCategoryDTO>> response = restTemplate.exchange(
                API_GET, HttpMethod.GET, null,
                new ParameterizedTypeReference<ResponseDTO<SubCategoryDTO>>() {
                });
        result = response.getBody();
        System.out.println(result);
        model.addAttribute(LIST, result.getListData());
    }

    @Override
    public void add(RequestDTO<SubCategoryDTO> requestDTO) {
        ResponseEntity<ResponseDTO<SubCategoryDTO>> response = restTemplate.exchange(
                API_CREATE, HttpMethod.POST, new HttpEntity<RequestDTO<SubCategoryDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<SubCategoryDTO>>() {
                });
        result = response.getBody();
    }

    @Override
    public void updatePage(String id, Model model) {
        RequestDTO<SubCategoryDTO> requestDTO = new RequestDTO<>();
        requestDTO.setId(id);
        ResponseEntity<ResponseDTO<SubCategoryDTO>> response = restTemplate.exchange(
                API_GET, HttpMethod.POST, new HttpEntity<RequestDTO<SubCategoryDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<SubCategoryDTO>>() {
                });
        result = response.getBody();
        model.addAttribute(OBJECT, result.getData());
    }

    public void update(RequestDTO<SubCategoryDTO> requestDTO) {
        ResponseEntity<ResponseDTO<SubCategoryDTO>> response = restTemplate.exchange(
                API_UPDATE, HttpMethod.POST, new HttpEntity<RequestDTO<SubCategoryDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<SubCategoryDTO>>() {
                });
        result = response.getBody();
    }

    @Override
    public void delete(String id) {
        RequestDTO<SubCategoryDTO> requestDTO = new RequestDTO<>();
        requestDTO.setId(id);
        ResponseEntity<ResponseDTO<SubCategoryDTO>> response = restTemplate.exchange(
                API_DELETE, HttpMethod.POST, new HttpEntity<RequestDTO<SubCategoryDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<SubCategoryDTO>>() {
                });
        result = response.getBody();
    }
}
