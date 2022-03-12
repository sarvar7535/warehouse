package pdp.uz.pricticelesson11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.pricticelesson11.entity.Input;
import pdp.uz.pricticelesson11.entity.InputProduct;
import pdp.uz.pricticelesson11.entity.Product;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.InputProductDTO;
import pdp.uz.pricticelesson11.repository.InputProductRepository;
import pdp.uz.pricticelesson11.repository.InputRepository;
import pdp.uz.pricticelesson11.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    public List<InputProduct> getAll() {
        List<InputProduct> inputProductList = inputProductRepository.findAll();
        return inputProductList;
    }

    public InputProduct getById(Integer id) {
        Optional<InputProduct> byId = inputProductRepository.findById(id);
        if (byId.isPresent()) {
            InputProduct inputProduct = byId.get();
            return inputProduct;
        }
        return new InputProduct();
    }

    public ApiResponse add(InputProductDTO inputProductDTO) {
        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
        if (!optionalProduct.isPresent())
            return new ApiResponse("Not found product ID", false);

        Optional<Input> optionalInput = inputRepository.findById(inputProductDTO.getInputId());
        if (!optionalInput.isPresent())
            return new ApiResponse("Not found input ID", false);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setAmount(inputProductDTO.getAmount());
        inputProduct.setPrice(inputProductDTO.getPrice());
        Date date = new Date();
        inputProduct.setExpireDate(new java.sql.Date(date.getTime()));

        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);

        return new ApiResponse("added", true);

    }

    public ApiResponse editInputProduct(Integer id, InputProductDTO inputProductDTO) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) {
            return new ApiResponse("Not found inputProduct ID", false);
        }

        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Not found product ID", false);

        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDTO.getInputId());
        if (!optionalInput.isPresent()) {
            return new ApiResponse("Not found input ID", false);
        }
        InputProduct inputProduct = optionalInputProduct.get();


        inputProduct.setAmount(inputProductDTO.getAmount());
        inputProduct.setPrice(inputProductDTO.getPrice());

        Date date = new Date();
        inputProduct.setExpireDate(new java.sql.Date(date.getTime()));

        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);

        return new ApiResponse("added", true);
    }

    public ApiResponse delete(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()) {
            inputProductRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("Not found inputProduct Id", false);
    }
}
