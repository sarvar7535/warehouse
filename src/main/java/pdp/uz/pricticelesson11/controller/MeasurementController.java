package pdp.uz.pricticelesson11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.pricticelesson11.entity.Measurement;
import pdp.uz.pricticelesson11.entity.Warehouse;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.service.MeasurementService;
import pdp.uz.pricticelesson11.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    @GetMapping("/getAll")
    public List<Measurement> getWarehouse(){
        List<Measurement> all = measurementService.getAll();
        return all;
    }
    @GetMapping("/byId/{id}")
    public Measurement getById(@PathVariable Integer id){
        Measurement byId = measurementService.getById(id);
        return byId;
    }
    @PostMapping("/add")
    public ApiResponse addW(@RequestBody Measurement measurement){
        return measurementService.addMeasurement(measurement);

    }

    @PutMapping("/edit/{id}")
    public ApiResponse editW(@PathVariable Integer id, @RequestBody Measurement measurement){
       return measurementService.edit(id,measurement);

    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse deletedW(@PathVariable Integer id){
       return measurementService.delete(id);
    }

}
