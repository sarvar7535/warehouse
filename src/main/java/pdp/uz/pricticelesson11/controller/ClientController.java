package pdp.uz.pricticelesson11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.pricticelesson11.entity.Client;
import pdp.uz.pricticelesson11.entity.ClientDTO;
import pdp.uz.pricticelesson11.entity.Supplier;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.SupplierDTO;
import pdp.uz.pricticelesson11.service.ClientService;
import pdp.uz.pricticelesson11.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/getAll")
    public List<Client> getWarehouse(){
        List<Client> all = clientService.getAll();
        return all;
    }
    @GetMapping("/byId/{id}")
    public Client getById(@PathVariable Integer id){
        Client byId = clientService.getById(id);
        return byId;
    }
    @PostMapping("/add")
    public ApiResponse addW(@RequestBody ClientDTO clientDTO){
        return clientService.add(clientDTO);

    }

    @PutMapping("/edit/{id}")
    public ApiResponse editW(@PathVariable Integer id, @RequestBody ClientDTO clientDTO){
       return clientService.edit(id,clientDTO);

    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse deletedW(@PathVariable Integer id){
       return clientService.delete(id);
    }

}
