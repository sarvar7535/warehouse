package pdp.uz.pricticelesson11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.pricticelesson11.entity.InputProduct;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.InputProductDTO;
import pdp.uz.pricticelesson11.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    @GetMapping("/getAllInputProduct")
    public List<InputProduct> getAll(){
      return inputProductService.getAll();

    }
    @GetMapping("/ById/{id}")
    public InputProduct ById(@PathVariable Integer id){
        return inputProductService.getById(id);
    }

    @PostMapping("/add")
    public ApiResponse added(@RequestBody InputProductDTO inputProductDTO){
        return inputProductService.add(inputProductDTO);
    }
    @PutMapping("/edit/{id}")
    public ApiResponse edited(@PathVariable Integer id, @RequestBody InputProductDTO inputProductDTO){
        return inputProductService.editInputProduct(id,inputProductDTO);
    }
    @DeleteMapping("/deleted/{id}")
    public ApiResponse deleteById(@PathVariable Integer id){
        return inputProductService.delete(id);
    }
}
