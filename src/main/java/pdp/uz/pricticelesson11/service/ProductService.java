package pdp.uz.pricticelesson11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.pricticelesson11.entity.Attachment;
import pdp.uz.pricticelesson11.entity.Category;
import pdp.uz.pricticelesson11.entity.Measurement;
import pdp.uz.pricticelesson11.entity.Product;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.ProductDTo;
import pdp.uz.pricticelesson11.repository.AttachmentRepository;
import pdp.uz.pricticelesson11.repository.CategoryRepository;
import pdp.uz.pricticelesson11.repository.MeasurementRepository;
import pdp.uz.pricticelesson11.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public List<Product> getAll(){
        List<Product> productList = productRepository.findAll();
        return productList;
    }
    public Product getById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            return product;
        }
        return new Product();
    }

    public ApiResponse addProduct(ProductDTo productDTo) {
        boolean exists = productRepository.existsByNameAndCategoryId(productDTo.getName(), productDTo.getCategoryId());
        if (exists){
            return new ApiResponse("Bunday maxsulot ushbu kategoriyada mavjud", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(productDTo.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Bunday kategoriiya mavjud emas", false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDTo.getAttachmentId());
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("Bunday attachment mavjud emas", false);

        }
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTo.getMeasurementId());
        if (!optionalMeasurement.isPresent()){
            return new ApiResponse("Bunday measurement mavjud emas", false);
        }
        Product product=new Product();
        product.setName(productDTo.getName());

        product.setCode(UUID.randomUUID().toString());

        product.setCategory(optionalCategory.get());
        product.setAttachment(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());

        productRepository.save(product);
        return new ApiResponse("Product saved", true);
    }
    public ApiResponse editProduct(Integer id, ProductDTo productDTo){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return new ApiResponse("Not found ID", false);

        boolean exists = productRepository.existsByNameAndCategoryId(productDTo.getName(), productDTo.getCategoryId());
        if (exists){
            return new ApiResponse("Bunday maxsulot ushbu kategoriyada mavjud", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(productDTo.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Bunday kategoriiya mavjud emas", false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDTo.getAttachmentId());
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("Bunday attachment mavjud emas", false);

        }
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTo.getMeasurementId());
        if (!optionalMeasurement.isPresent()){
            return new ApiResponse("Bunday measurement mavjud emas", false);
        }
        Product product = optionalProduct.get();

        product.setCode(UUID.randomUUID().toString());

        product.setCategory(optionalCategory.get());
        product.setAttachment(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());

        product.setName(productDTo.getName());

        productRepository.save(product);
        return new ApiResponse("Product saved", true);
    }
    public ApiResponse deleteById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("Not found Id", false);
    }

}
