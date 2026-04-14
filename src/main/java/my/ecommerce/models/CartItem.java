package my.ecommerce.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int qty;

    @ManyToOne
    private CartEntity cart;

    @ManyToOne
    private ProductsEntity product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cartEntity) {
        this.cart = cartEntity;
    }

    public ProductsEntity getProduct() {
        return product;
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }
}
