package my.ecommerce.service;

import my.ecommerce.models.CategoryEntity;
import my.ecommerce.models.ProductsEntity;
import my.ecommerce.repository.CategoryRepository;
import my.ecommerce.repository.ProductsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final CategoryRepository categoryRepository;

    public ProductsService(ProductsRepository productsRepository,
                           CategoryRepository categoryRepository) {
        this.productsRepository = productsRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductsEntity> getAllProducts(){
        return productsRepository.findAll();
    }

    public Page<ProductsEntity> getLimitedProducts(int max) {
        Pageable maxPages = PageRequest.of(0, max);
        return productsRepository.findAll(maxPages);

    }
    public ProductsEntity insertProduct(Long cat_id, String prodName, float price) {
        CategoryEntity category = categoryRepository.findById(cat_id)
                .orElseThrow(() -> new RuntimeException("invalid category"));
        ProductsEntity product = new ProductsEntity(prodName, price);
        product.setCategory(category);
        return productsRepository.save(product);
    }

}
