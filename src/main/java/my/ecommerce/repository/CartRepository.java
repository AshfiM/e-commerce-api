package my.ecommerce.repository;


import my.ecommerce.models.CartEntity;
import my.ecommerce.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    CartEntity findByUserId(UserEntity userId);
}
