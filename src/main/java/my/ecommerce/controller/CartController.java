package my.ecommerce.controller;

import my.ecommerce.dto.CartDto;
import my.ecommerce.dto.CartItemDto;
import my.ecommerce.models.CartEntity;
import my.ecommerce.security.JwtService;
import my.ecommerce.service.CartService;
import my.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final JwtService jwtService;
    public CartController(CartService cartService, JwtService jwtService) {

        this.cartService = cartService;
        this.jwtService = jwtService;
    }
    @GetMapping("/")
    public ResponseEntity<CartDto> getCart(@CookieValue(name = "jwt-token") String token){
        if (!jwtService.validateToken(token)){
            return ResponseEntity.badRequest().build();
        }
        String userId = jwtService.extractUsername(token);
        //System.out.println(userId);
        CartDto response = cartService.cartEntityToCartDto(cartService.getCart(userId));
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/addItemToCart", consumes = "application/json")
    public ResponseEntity<CartDto> addToCart(@CookieValue(name = "jwt-token") String token,
                                             @RequestBody CartItemDto cartItemDto) {
        if (!jwtService.validateToken(token)) {
            return ResponseEntity.badRequest().build();
        }

        String userId = jwtService.extractUsername(token);
        //System.out.println(userId);
        CartDto newCart = cartService.addItemToCart(cartItemDto, userId);
        return ResponseEntity.ok(newCart);
    }
}
