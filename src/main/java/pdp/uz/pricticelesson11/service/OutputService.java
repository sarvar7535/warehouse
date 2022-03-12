package pdp.uz.pricticelesson11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.pricticelesson11.entity.*;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.InputDTO;
import pdp.uz.pricticelesson11.payload.OutputDTO;
import pdp.uz.pricticelesson11.repository.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;

    public List<Output> getAll(){
        List<Output> outputs = outputRepository.findAll();
        return outputs;
    }
    public Output ByIdGet(Integer id){
        Optional<Output> optionalInput = outputRepository.findById(id);
        if (optionalInput.isPresent()){
            Output output = optionalInput.get();
            return output;
        }
        return new Output();
    }

    public ApiResponse add(OutputDTO outputDTO){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouseId());
        if (!optionalWarehouse.isPresent()) return new ApiResponse("Not found Warehouse Id", false);

        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClientId());
        if (!optionalClient.isPresent()) return new ApiResponse("Not found client id", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent()) return new ApiResponse("Not found currency id", false);

        boolean exists = outputRepository.existsByFactureNumber(outputDTO.getFactureNumber());
        if (exists) return new ApiResponse("this is Facture number already exists", false);

        Date date=new Date();
        Output output=new Output();
        output.setDate(new Timestamp(date.getTime()));
        output.setFactureNumber(outputDTO.getFactureNumber());
        output.setCode(UUID.randomUUID().toString());

        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());

        output.setCurrency(optionalCurrency.get());

        outputRepository.save(output);

        return new ApiResponse("added", true);

    }
    public ApiResponse edit(Integer id, OutputDTO outputDTO){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) return new ApiResponse("Not found input id", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouseId());
        if (!optionalWarehouse.isPresent()) return new ApiResponse("Not found Warehouse Id", false);


        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClientId());
        if (!optionalClient.isPresent()) return new ApiResponse("Not found client id", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent()) return new ApiResponse("Not found currency id", false);

        boolean exists = outputRepository.existsByFactureNumber(outputDTO.getFactureNumber());
        if (exists) return new ApiResponse("this is Facture number already exists", false);

        Output output = optionalOutput.get();
        Date date=new Date();
        output.setDate(new Timestamp(date.getTime()));

        output.setFactureNumber(outputDTO.getFactureNumber());
        output.setCode(UUID.randomUUID().toString());

        output.setWarehouse(optionalWarehouse.get());

        output.setClient(optionalClient.get());

        output.setCurrency(optionalCurrency.get());

        outputRepository.save(output);

        return new ApiResponse("added", true);

    }
    public ApiResponse delete(Integer id){
        Optional<Output> byId = outputRepository.findById(id);
        if (byId.isPresent()){
            outputRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }
        return new ApiResponse("not found Id", false);
    }

}
