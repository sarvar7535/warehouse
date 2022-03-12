package pdp.uz.pricticelesson11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.pricticelesson11.entity.Warehouse;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public List<Warehouse> getAll() {
        List<Warehouse> all = warehouseRepository.findAll();
        return all;
    }

    public Warehouse getById(Integer id) {
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        if (byId.isPresent()) {
            Warehouse warehouse = byId.get();
            return warehouse;
        }
        return new Warehouse();

    }

    public ApiResponse addWarehouse(Warehouse warehouse) {
        Warehouse warehouse1 = new Warehouse();

        if (warehouseRepository.existsByName(warehouse.getName())) {
            return new ApiResponse("Bunday nameli warehouse mavjud", false);
        }
        warehouse1.setName(warehouse.getName());
        warehouseRepository.save(warehouse1);
        return new ApiResponse("saved", true);

    }

    public ApiResponse edit(Integer id, Warehouse warehouse) {
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        boolean exists = warehouseRepository.existsByName(warehouse.getName());
        if (!byId.isPresent()){
            return new ApiResponse("Not found Id", false);
        }
        if (exists){
            return new ApiResponse("Bunday nameli warehouse mavjud", false);
        }
        Warehouse warehouse1 = byId.get();
        warehouse1.setName(warehouse.getName());
        warehouseRepository.save(warehouse1);
        return new ApiResponse("saved", true);

    }
    public ApiResponse delete(Integer id){
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        if (byId.isPresent()){
            warehouseRepository.deleteById(id);
            return new ApiResponse("warehouse deleted", true);
        }
        return new ApiResponse("not found Id", false);
    }


}
