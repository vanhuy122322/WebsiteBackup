package fa.training.spring.controller.client.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.product.ProductDTO;
import fa.training.spring.service.product.ProductService;
import fa.training.spring.service.shop.ShopService;
import fa.training.spring.util.ImportExcelProduct;

@Controller
@RequestMapping("/shop")
public class ProductShopController {

	ImportExcelProduct importExcelProduct = new ImportExcelProduct();

	@Autowired
	ShopService shopService;

	@Autowired
	ProductService productService;

	@PostMapping("/product/add")
	public String add(ProductDTO productDTO) {
		RequestDTO<ProductDTO> requestDTO = new RequestDTO<ProductDTO>(productDTO);
		String name = requestDTO.getData().getShopName();
		productService.add(requestDTO);
		return "redirect:/shop/product/" + name;
	}

	@GetMapping("/product/{name}/update")
	public String update(@PathVariable("name") String name, String id, Model model) {
		shopService.getOneByName(name, model);
		productService.updatePage(id, model);
		return "pages/client/shop/UpdateProductShop";
	}

	@GetMapping("/product/add/{name}")
	public String addProduct(@PathVariable("name") String name, Model model) {
		shopService.getOneByName(name, model);
		shopService.getProductByName(name, model);
		return "pages/client/shop/CreateProductShop";
	}

	@PostMapping("/product/update")
	public String update(ProductDTO productDTO) {
		RequestDTO<ProductDTO> requestDTO = new RequestDTO<ProductDTO>(productDTO);
		productService.update(requestDTO);
		return "redirect:/shop/" + productDTO.getShopName();
	}

	@PostMapping("/product/delete")
	@ResponseBody
	public void delete(String id) {
		productService.delete(id);
	}

	@GetMapping("/product-file/{name}")
	public String importProduct(@PathVariable("name") String name, String id, Model model) {
		shopService.getOneByName(name, model);
		shopService.getProductByName(name, model);
		return "pages/client/shop/ImportProductShop";
	}

	@PostMapping("/product-file")
	public String passFileToDTO(MultipartFile file) {
    	productService.sendDataFromExcelToAPIAndSave(importExcelProduct.readExcelFile(file));
    	String shopName="";
    	for(ProductDTO productDTO: importExcelProduct.readExcelFile(file).getListData()) {
    		shopName=productDTO.getShopName();
    	}
    	return "redirect:/shop/"+shopName;
    	
	}

}
