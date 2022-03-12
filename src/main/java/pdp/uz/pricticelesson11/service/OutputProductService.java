package pdp.uz.pricticelesson11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.pricticelesson11.entity.*;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.InputProductDTO;
import pdp.uz.pricticelesson11.payload.OutputProductDTO;
import pdp.uz.pricticelesson11.repository.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public List<OutputProduct> getAll() {
        List<OutputProduct> all = outputProductRepository.findAll();
        return all;
    }

    public OutputProduct getById(Integer id) {
        Optional<OutputProduct> byId = outputProductRepository.findById(id);
        if (byId.isPresent()) {
            OutputProduct outputProduct = byId.get();
            return outputProduct;
        }
        return new OutputProduct();
    }

    public ApiResponse add(OutputProductDTO outputProductDTO) {
        Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
        if (!optionalProduct.isPresent())
            return new ApiResponse("Not found product ID", false);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutputId());
        if (!optionalOutput.isPresent())
            return new ApiResponse("Not found Output ID", false);

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProduct.getAmount());
        outputProduct.setPrice(outputProduct.getPrice());


        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);

        return new ApiResponse("added", true);

    }

    public ApiResponse editInputProduct(Integer id, OutputProductDTO outputProductDTO) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) {
            return new ApiResponse("Not found inputProduct ID", false);
        }

        Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Not found product ID", false);

        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutputId());
        if (!optionalOutput.isPresent()) {
            return new ApiResponse("Not found Output ID", false);
        }
        OutputProduct outputProduct = optionalOutputProduct.get();


        outputProduct.setAmount(outputProductDTO.getAmount());
        outputProduct.setPrice(outputProductDTO.getPrice());

        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);

        return new ApiResponse("added", true);
    }

    public ApiResponse delete(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()) {
            outputProductRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("Not found OutputProduct Id", false);
    }
}
