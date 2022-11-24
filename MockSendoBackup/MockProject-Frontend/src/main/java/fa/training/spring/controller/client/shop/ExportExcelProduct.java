package fa.training.spring.controller.client.shop;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.spring.dto.product.ProductDTO;
import fa.training.spring.service.product.ProductService;
import fa.training.spring.service.shop.ExportExcelProductService;
import fa.training.spring.service.shop.ShopService;
import fa.training.spring.util.ImportExcelProduct;

@Controller
@RequestMapping("/shop")
public class ExportExcelProduct {
	

	@Autowired
	ExportExcelProductService excelProductService;
	
	@Autowired
	ShopService shopService;

	@Autowired
	ProductService productService;

	@GetMapping("/product/{name}/export")
	public String exportExcel(@PathVariable("name") String name, String id, Model model) {
		shopService.getOneByName(name, model);
		productService.updatePage(id, model);
		return "pages/client/shop/ExportExcelProductShop";
	}
	
	@GetMapping("/export-excel")
    public void exportToExcel(HttpServletResponse response, String name) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=products_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<ProductDTO> productDTOs = shopService.getProductByShopName(name);
        ImportExcelProduct excelExporter = new ImportExcelProduct(productDTOs);
        excelExporter.export(response);
    }

	

}


