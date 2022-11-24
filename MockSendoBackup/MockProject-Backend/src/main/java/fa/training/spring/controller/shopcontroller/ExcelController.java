package fa.training.spring.controller.shopcontroller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.spring.Util.ExcelExporter;
import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.ProductDTO;
import fa.training.spring.dto.shopdto.ShopDTO;
import fa.training.spring.service.productservice.ProductService;
@Controller
@RequestMapping("/api/v1/excel/products")
public class ExcelController {
    
    @Autowired
    ProductService productService;
    
    @GetMapping
    public String exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=products_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        System.out.println(productService.getAll());
        List<ProductDTO> productDTOs = productService.getAll();
        ExcelExporter excelExporter = new ExcelExporter(productDTOs);
        return excelExporter.export(response);
    }
}
