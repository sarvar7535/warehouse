package pdp.uz.pricticelesson11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.pricticelesson11.entity.Category;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.CategoryDTO;
import pdp.uz.pricticelesson11.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")


public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public ApiResponse addCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.addCategory(categoryDTO);

    }

    @GetMapping("/getAll")
    public List<Category> getAllCategory() {
        return categoryService.getAll();
    }

    @GetMapping("/getByID/{id}")
    public Category getById(@PathVariable Integer id) {
        return categoryService.getByID(id);
    }

    @PutMapping("edit/{id}")
    public ApiResponse editCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
       return categoryService.editCategory(id, categoryDTO);

    }

    @DeleteMapping("/deleted/{id}")
    public ApiResponse deletedById(@PathVariable Integer id) {
        return categoryService.delete(id);
    }


}
