package pdp.uz.pricticelesson11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.pricticelesson11.entity.Supplier;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.SupplierDTO;
import pdp.uz.pricticelesson11.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @GetMapping("/getAll")
    public List<Supplier> getWarehouse(){
        List<Supplier> all = supplierService.getAll();
        return all;
    }
    @GetMapping("/byId/{id}")
    public Supplier getById(@PathVariable Integer id){
        Supplier byId = supplierService.getById(id);
        return byId;
    }
    @PostMapping("/add")
    public ApiResponse addW(@RequestBody SupplierDTO supplierDTO){
        return supplierService.add(supplierDTO);

    }

    @PutMapping("/edit/{id}")
    public ApiResponse editW(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO){
       return supplierService.edit(id,supplierDTO);

    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse deletedW(@PathVariable Integer id){
       return supplierService.delete(id);
    }

}
