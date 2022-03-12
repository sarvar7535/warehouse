package pdp.uz.pricticelesson11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.pricticelesson11.entity.Product;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.ProductDTo;
import pdp.uz.pricticelesson11.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/getAll")
    public List<Product> getProduct(){
        return productService.getAll();
    }
    @GetMapping("/getById/{id}")
    public Product getById(@PathVariable Integer id){
        return productService.getById(id);
    }

    @PostMapping("/add")
    public ApiResponse addProduct(@RequestBody ProductDTo productDTo){
        ApiResponse apiResponse = productService.addProduct(productDTo);
        return apiResponse;
    }
    @PutMapping("/edit/{id}")
    public ApiResponse editProduct(@PathVariable Integer id, @RequestBody ProductDTo productDTo){
        return productService.editProduct(id,productDTo);
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return productService.deleteById(id);
    }
}
