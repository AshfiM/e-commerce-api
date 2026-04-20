package my.ecommerce.repository;

import my.ecommerce.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, Long> {

}
