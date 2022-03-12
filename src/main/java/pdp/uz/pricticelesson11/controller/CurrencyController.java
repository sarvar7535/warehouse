package pdp.uz.pricticelesson11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.pricticelesson11.entity.Currency;
import pdp.uz.pricticelesson11.entity.Warehouse;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.service.CurrencyService;
import pdp.uz.pricticelesson11.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @GetMapping("/getAll")
    public List<Currency> getWarehouse(){
        List<Currency> all = currencyService.getAll();
        return all;
    }
    @GetMapping("/byId/{id}")
    public Currency getById(@PathVariable Integer id){
        Currency byId = currencyService.getById(id);
        return byId;
    }
    @PostMapping("/add")
    public ApiResponse addW(@RequestBody Currency currency){
        return currencyService.addWarehouse(currency);

    }

    @PutMapping("/edit/{id}")
    public ApiResponse editW(@PathVariable Integer id, @RequestBody Currency currency){
       return currencyService.edit(id,currency);

    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse deletedW(@PathVariable Integer id){
       return currencyService.delete(id);
    }

}
