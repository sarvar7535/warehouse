package pdp.uz.pricticelesson11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.pricticelesson11.entity.Warehouse;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @GetMapping("/getAll")
    public List<Warehouse> getWarehouse(){
        List<Warehouse> all = warehouseService.getAll();
        return all;
    }
    @GetMapping("/byId/{id}")
    public Warehouse getById(@PathVariable Integer id){
        Warehouse byId = warehouseService.getById(id);
        return byId;
    }
    @PostMapping("/add")
    public ApiResponse addW(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);

    }

    @PutMapping("/edit/{id}")
    public ApiResponse editW(@PathVariable Integer id, @RequestBody Warehouse warehouse){
       return warehouseService.edit(id,warehouse);

    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse deletedW(@PathVariable Integer id){
       return warehouseService.delete(id);
    }

}
