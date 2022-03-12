package pdp.uz.pricticelesson11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.pricticelesson11.entity.Currency;
import pdp.uz.pricticelesson11.entity.Warehouse;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.repository.CurrencyRepository;
import pdp.uz.pricticelesson11.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public List<Currency> getAll() {
        List<Currency> all = currencyRepository.findAll();
        return all;
    }

    public Currency getById(Integer id) {
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isPresent()) {
            Currency currency = byId.get();
            return currency;
        }
        return new Currency();

    }

    public ApiResponse addWarehouse(Currency currency) {
        Currency currency1 = new Currency();

        if (currencyRepository.existsByName(currency.getName())) {
            return new ApiResponse("Bunday nameli Currency mavjud", false);
        }
        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return new ApiResponse("saved", true);

    }

    public ApiResponse edit(Integer id, Currency currency) {
        Optional<Currency> byId = currencyRepository.findById(id);
        boolean exists = currencyRepository.existsByName(currency.getName());
        if (!byId.isPresent()){
            return new ApiResponse("Not found Id", false);
        }
        if (exists){
            return new ApiResponse("Bunday nameli Currency mavjud", false);
        }
        Currency currency1 = byId.get();
        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return new ApiResponse("saved", true);

    }
    public ApiResponse delete(Integer id){
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isPresent()){
            currencyRepository.deleteById(id);
            return new ApiResponse("Currency deleted", true);
        }
        return new ApiResponse("not found Id", false);
    }


}
