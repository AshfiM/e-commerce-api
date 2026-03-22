package my.ecommerce.repository;

import my.ecommerce.models.ProductsEntity;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {

}
