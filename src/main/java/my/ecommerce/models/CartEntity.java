package my.ecommerce.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItemEntity> cartItemEntities = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity user_id) {
        this.userId = user_id;
    }

    public List<CartItemEntity> getCartItems() {
        return cartItemEntities;
    }

    public void setCartItems(List<CartItemEntity> cartItemEntities) {
        this.cartItemEntities = cartItemEntities;
    }
}
