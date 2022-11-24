package fa.training.spring.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.product.CategoryDTO;
import fa.training.spring.service.product.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String getAll(Model model) {
        categoryService.getAll(model);
        CategoryDTO categoryDTO = new CategoryDTO();
        model.addAttribute("categoryDTO", categoryDTO);
        return "pages/admin/category/ViewCategory";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("categoryDTO") CategoryDTO categoryDTO) {
        RequestDTO<CategoryDTO> requestDTO = new RequestDTO<CategoryDTO>(categoryDTO);
        categoryService.add(requestDTO);
        return "redirect:/admin/category";
    }

    @GetMapping("/update")
    public String update(String id, Model model) {
        categoryService.updatePage(id, model);
        CategoryDTO categoryDTO = new CategoryDTO();
        model.addAttribute("categoryDTO", categoryDTO);
        return "pages/admin/category/UpdateCategory";
    }

    @GetMapping("/subcategory")
    public String updateSubCategory(String id, Model model) {
        categoryService.updatePage(id, model);
        return "pages/admin/category/subcategory/ViewSubCategory";
    }

    @PostMapping("/update")
    public String update(CategoryDTO categoryDTO) {
        System.out.println(categoryDTO);
        RequestDTO<CategoryDTO> requestDTO = new RequestDTO<CategoryDTO>(categoryDTO);
        categoryService.update(requestDTO);
        return "redirect:/admin/category/update?id=" + categoryDTO.getId();
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(String id) {
        categoryService.deleteCategory(id);
    }

    @PostMapping("/deletesub")
    @ResponseBody
    public String approve(CategoryDTO categoryDTO) {
        System.out.println(categoryDTO);
        RequestDTO<CategoryDTO> requestDTO = new RequestDTO<CategoryDTO>(categoryDTO);
        categoryService.update(requestDTO);
        return "redirect:/admin/category/subcategory?id=" + categoryDTO.getId();
    }

    @PostMapping("/search")
    @ResponseBody
    public CategoryDTO delete(CategoryDTO categoryDTO) {
        RequestDTO<CategoryDTO> requestDTO = new RequestDTO<CategoryDTO>(categoryDTO);
        return categoryService.search(requestDTO);
    }

    public void deleteSubCategory(String id, String slug) {
        categoryService.deleteSubCategory(id, slug);
    }

}
