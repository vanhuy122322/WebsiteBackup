package fa.training.spring.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.product.ProductDTO;
import fa.training.spring.service.product.ProductService;

@Controller
@RequestMapping("/admin/product")
public class ProductAdminController {

    @Autowired
    ProductService productService;

    @GetMapping
    public String getAll(Model model) {
        productService.getAll(model);
        return "pages/admin/product/ViewProduct";
    }

    @PostMapping("/add")
    public String add(ProductDTO productDTO) {
        RequestDTO<ProductDTO> requestDTO = new RequestDTO<ProductDTO>(productDTO);
        productService.add(requestDTO);
        return "redirect:/admin/product";
    }

    @GetMapping("/update")
    public String update(String id, Model model) {
        productService.updatePage(id, model);
        return "pages/admin/product/UpdateProduct";
    }

    @PostMapping("/update")
    public String update(ProductDTO productDTO) {
        System.out.println(productDTO);
        RequestDTO<ProductDTO> requestDTO = new RequestDTO<ProductDTO>(productDTO);
        productService.update(requestDTO);
        return "redirect:/admin/product/update?id=" + productDTO.getId();
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(String id) {
        productService.delete(id);
        return "a";
    }
}
