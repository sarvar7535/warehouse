package pdp.uz.pricticelesson11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.pricticelesson11.entity.Currency;
import pdp.uz.pricticelesson11.entity.Input;
import pdp.uz.pricticelesson11.entity.Supplier;
import pdp.uz.pricticelesson11.entity.Warehouse;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.InputDTO;
import pdp.uz.pricticelesson11.repository.CurrencyRepository;
import pdp.uz.pricticelesson11.repository.InputRepository;
import pdp.uz.pricticelesson11.repository.SupplierRepository;
import pdp.uz.pricticelesson11.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public List<Input> getAll(){
        List<Input> inputs = inputRepository.findAll();
        return inputs;
    }
    public Input ByIdGet(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()){
            Input input = optionalInput.get();
            return input;
        }
        return new Input();
    }

    public ApiResponse add(InputDTO inputDTO){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
        if (!optionalWarehouse.isPresent()) return new ApiResponse("Not found Warehouse Id", false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        if (!optionalSupplier.isPresent()) return new ApiResponse("Not found supplier Id", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent()) return new ApiResponse("Not found currency id", false);

        boolean exists = inputRepository.existsByFactureNumber(inputDTO.getFactureNumber());
        if (exists) return new ApiResponse("this is Facture number already exists", false);

        Date date=new Date();
        Input input=new Input();
        input.setDate(new Timestamp(date.getTime()));
        input.setFactureNumber(inputDTO.getFactureNumber());
        input.setCode(UUID.randomUUID().toString());

        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());

        inputRepository.save(input);

        return new ApiResponse("added", true);

    }
    public ApiResponse edit(Integer id, InputDTO inputDTO){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) return new ApiResponse("Not found input id", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
        if (!optionalWarehouse.isPresent()) return new ApiResponse("Not found Warehouse Id", false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        if (!optionalSupplier.isPresent()) return new ApiResponse("Not found supplier Id", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent()) return new ApiResponse("Not found currency id", false);

        boolean exists = inputRepository.existsByFactureNumber(inputDTO.getFactureNumber());
        if (exists) return new ApiResponse("this is Facture number already exists", false);

        Input input = optionalInput.get();
        Date date=new Date();
        input.setDate(new Timestamp(date.getTime()));

        input.setFactureNumber(inputDTO.getFactureNumber());
        input.setCode(UUID.randomUUID().toString());

        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());

        inputRepository.save(input);

        return new ApiResponse("added", true);

    }
    public ApiResponse delete(Integer id){
        Optional<Input> byId = inputRepository.findById(id);
        if (byId.isPresent()){
            inputRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }
        return new ApiResponse("not found Id", false);
    }

}
