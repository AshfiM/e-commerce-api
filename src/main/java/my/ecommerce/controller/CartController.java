package my.ecommerce.controller;

import my.ecommerce.dto.CartDto;
import my.ecommerce.models.CartEntity;
import my.ecommerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
//    @GetMapping("/{cartId}")
//    public ResponseEntity<CartDto> getCart(@PathVariable Long cartId){
//        CartEntity cart = cartService.getCart(cartId);
//        CartDto cartDto = new CartDto();
//        cartDto.setCartId((cart.));
//
//    }
}
