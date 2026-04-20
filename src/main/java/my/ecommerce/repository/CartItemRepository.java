package my.ecommerce.repository;

import my.ecommerce.models.CartEntity;
import my.ecommerce.models.CartItemEntity;
import my.ecommerce.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    Optional<CartItemEntity> findByCartAndProduct(CartEntity cart, ProductEntity product);
}
