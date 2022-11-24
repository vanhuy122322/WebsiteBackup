package fa.training.spring.service.product.serviceimpl;

import java.util.ArrayList;
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
import fa.training.spring.dto.product.SubCategoryDTO;
import fa.training.spring.service.product.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    /**
     *
     */
    private static final String LIST = "categories";
    private static final String OBJECT = "category";
    private final static String API_URL = "http://localhost:8080/api/v1/";
    private final static String API_GET = API_URL + "category/";
    private final static String API_GET_STATUS = API_URL + "category/status";
    private final static String API_CREATE = API_GET + "add";
    private final static String API_UPDATE = API_GET + "update";
    private final static String API_DELETE = API_GET + "delete";
    private final static String API_DELETE_SUB = API_GET + "deletesub";
    private final static String API_SEARCH = API_GET + "search";

    @Autowired
    RestTemplate restTemplate;

    private ResponseDTO<CategoryDTO> result;

    public void getAll(Model model) {
        ResponseEntity<ResponseDTO<CategoryDTO>> response = restTemplate.exchange(
                API_GET, HttpMethod.GET, null,
                new ParameterizedTypeReference<ResponseDTO<CategoryDTO>>() {
                });
        result = response.getBody();
        System.out.println(result);
        model.addAttribute(LIST, result.getListData());
    }

    public void getAllCategoryByStatus(Model model) {
        ResponseEntity<ResponseDTO<CategoryDTO>> response = restTemplate.exchange(
                API_GET_STATUS, HttpMethod.GET, null,
                new ParameterizedTypeReference<ResponseDTO<CategoryDTO>>() {
                });
        result = response.getBody();
        System.out.println(result);
        model.addAttribute(LIST, result.getListData());
    }

    @Override
    public void add(RequestDTO<CategoryDTO> requestDTO) {
        ResponseEntity<ResponseDTO<CategoryDTO>> response = restTemplate.exchange(
                API_CREATE, HttpMethod.POST, new HttpEntity<RequestDTO<CategoryDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<CategoryDTO>>() {
                });
        result = response.getBody();
    }

    @Override
    public void updatePage(String id, Model model) {
        RequestDTO<CategoryDTO> requestDTO = new RequestDTO<>();
        requestDTO.setId(id);
        ResponseEntity<ResponseDTO<CategoryDTO>> response = restTemplate.exchange(
                API_GET, HttpMethod.POST, new HttpEntity<RequestDTO<CategoryDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<CategoryDTO>>() {
                });
        result = response.getBody();
        model.addAttribute(OBJECT, result.getData());
    }

    public void update(RequestDTO<CategoryDTO> requestDTO) {
        ResponseEntity<ResponseDTO<CategoryDTO>> response = restTemplate.exchange(
                API_UPDATE, HttpMethod.POST, new HttpEntity<RequestDTO<CategoryDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<CategoryDTO>>() {
                });
        result = response.getBody();
    }

    @Override
    public void deleteCategory(String id) {
        RequestDTO<CategoryDTO> requestDTO = new RequestDTO<>();
        requestDTO.setId(id);
        ResponseEntity<ResponseDTO<CategoryDTO>> response = restTemplate.exchange(
                API_DELETE, HttpMethod.POST, new HttpEntity<RequestDTO<CategoryDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<CategoryDTO>>() {
                });
        result = response.getBody();
    }

    public void deleteSubCategory(String id, String slug) {
        RequestDTO<CategoryDTO> requestDTO = new RequestDTO<>();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(id);
        List<SubCategoryDTO> subCategoryDTOs = new ArrayList<>();
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.setSlug(slug);
        subCategoryDTOs.add(subCategoryDTO);
        categoryDTO.setSubCategories(subCategoryDTOs);
        requestDTO.setData(categoryDTO);
        ResponseEntity<ResponseDTO<CategoryDTO>> response = restTemplate.exchange(
                API_DELETE_SUB, HttpMethod.POST, new HttpEntity<RequestDTO<CategoryDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<CategoryDTO>>() {
                });
        result = response.getBody();
    }

    @Override
    public CategoryDTO search(RequestDTO<CategoryDTO> requestDTO) {
        ResponseEntity<ResponseDTO<CategoryDTO>> response = restTemplate.exchange(
                API_SEARCH, HttpMethod.POST, new HttpEntity<RequestDTO<CategoryDTO>>(requestDTO),
                new ParameterizedTypeReference<ResponseDTO<CategoryDTO>>() {
                });
        result = response.getBody();
        return result.getData();
    }

}
