package pdp.uz.pricticelesson11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.pricticelesson11.entity.Supplier;
import pdp.uz.pricticelesson11.entity.Warehouse;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.SupplierDTO;
import pdp.uz.pricticelesson11.repository.SupplierRepository;
import pdp.uz.pricticelesson11.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public List<Supplier> getAll() {
        List<Supplier> all = supplierRepository.findAll();
        return all;
    }

    public Supplier getById(Integer id) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isPresent()) {
            Supplier supplier = byId.get();
            return supplier;
        }
        return new Supplier();

    }

    public ApiResponse add(SupplierDTO supplierDTO) {
        Supplier supplier1 = new Supplier();

        if (supplierRepository.existsByPhoneNumber(supplierDTO.getPhoneNumber())) {
            return new ApiResponse("Bunday phoneNumber li Supplier mavjud", false);
        }
        supplier1.setName(supplierDTO.getName());
        supplier1.setPhoneNumber(supplierDTO.getPhoneNumber());
        supplierRepository.save(supplier1);
        return new ApiResponse("saved", true);

    }

    public ApiResponse edit(Integer id, SupplierDTO supplierDTO) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        boolean exists = supplierRepository.existsByPhoneNumber(supplierDTO.getPhoneNumber());
        if (!byId.isPresent()){
            return new ApiResponse("Not found Id", false);
        }
        if (exists){
            return new ApiResponse("Bunday phoneNumber li Supplier mavjud", false);
        }
        Supplier supplier = byId.get();
        supplier.setName(supplierDTO.getName());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
        supplierRepository.save(supplier);
        return new ApiResponse("saved", true);

    }
    public ApiResponse delete(Integer id){
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isPresent()){
            supplierRepository.deleteById(id);
            return new ApiResponse("Supplier deleted", true);
        }
        return new ApiResponse("not found Id", false);
    }


}
