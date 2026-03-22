package my.ecommerce.controller;

import my.ecommerce.dto.CategoryDto;
import my.ecommerce.models.CategoryEntity;
import my.ecommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/cats")
    public ResponseEntity<List<CategoryDto>> getCats(){
        return  ResponseEntity.ok(categoryService.getCats().stream().map((cat)->{
            CategoryDto newCat = new CategoryDto();
            newCat.setCat_name(cat.getCat_name());
            newCat.setCat_id(cat.getCat_id());
            return newCat;
        }).toList());
    }
    @PostMapping(value = "/insert", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CategoryDto> insertCat(@RequestBody CategoryDto category){
        CategoryEntity newCategory = categoryService.insertCategory(category.getCat_name());
        CategoryDto cat = new CategoryDto();
        cat.setCat_id(newCategory.getCat_id());
        cat.setCat_name(category.getCat_name());
        return ResponseEntity.ok(cat);
    }


}
