package my.ecommerce.service;

import my.ecommerce.models.CategoryEntity;
import my.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> getCats(){
        return categoryRepository.findAll().stream().toList();
    }

    public CategoryEntity insertCategory(String catName){
        if (catName.isBlank()) {
            throw new RuntimeException("invalid category");
        }
        CategoryEntity cat = new CategoryEntity(catName);
        return categoryRepository.save(cat);
    }
}
