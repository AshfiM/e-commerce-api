package my.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private Long cartId;
    private List<CartItemDto> cartItems = new ArrayList<>();

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }
}
