package pdp.uz.pricticelesson11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.pricticelesson11.entity.Category;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.CategoryDTO;
import pdp.uz.pricticelesson11.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        List<Category> all = categoryRepository.findAll();
        return all;
    }

    public Category getByID(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            return category;
        }
        return new Category();

    }

    public ApiResponse addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        if (categoryDTO.getParentCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getParentCategoryId());
            if (!optionalCategory.isPresent())
                return new ApiResponse("Bunday ota kategory mavjud emas", false);
            category.setParentCategory(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new ApiResponse("Successfully", true);
    }

    public ApiResponse editCategory(Integer id, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new ApiResponse("Not found ID", false);
        Category category = optionalCategory.get();
        category.setName(categoryDTO.getName());

        if (categoryDTO.getParentCategoryId() != null) {
            Optional<Category> categoryRepositoryById = categoryRepository.findById(categoryDTO.getParentCategoryId());
            if (!categoryRepositoryById.isPresent())
                return new ApiResponse("Bunday ota kategory mavjud emas", false);

            category.setParentCategory(categoryRepositoryById.get());
        }
        categoryRepository.save(category);
        return new ApiResponse("Successfully", true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Category> categoryRepositoryById = categoryRepository.findById(id);
        if (categoryRepositoryById.isPresent()) {
            categoryRepository.deleteById(id);
            return new ApiResponse("deleted category", true);
        }
        return new ApiResponse("Not found Id", false);
    }
}
