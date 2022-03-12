package pdp.uz.pricticelesson11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.pricticelesson11.entity.InputProduct;
import pdp.uz.pricticelesson11.entity.OutputProduct;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.InputProductDTO;
import pdp.uz.pricticelesson11.payload.OutputProductDTO;
import pdp.uz.pricticelesson11.service.InputProductService;
import pdp.uz.pricticelesson11.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;

    @GetMapping("/getAllInputProduct")
    public List<OutputProduct> getAll(){
      return outputProductService.getAll();

    }
    @GetMapping("/ById/{id}")
    public OutputProduct ById(@PathVariable Integer id){
        return outputProductService.getById(id);
    }

    @PostMapping("/add")
    public ApiResponse added(@RequestBody OutputProductDTO outputProductDTO){
        return outputProductService.add(outputProductDTO);
    }
    @PutMapping("/edit/{id}")
    public ApiResponse edited(@PathVariable Integer id, @RequestBody OutputProductDTO outputProductDTO){
        return outputProductService.editInputProduct(id,outputProductDTO);
    }
    @DeleteMapping("/deleted/{id}")
    public ApiResponse deleteById(@PathVariable Integer id){
        return outputProductService.delete(id);
    }
}
