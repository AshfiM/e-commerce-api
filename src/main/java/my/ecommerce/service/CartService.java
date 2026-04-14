package my.ecommerce.service;

import my.ecommerce.dto.CartDto;
import my.ecommerce.models.CartEntity;
import my.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

//    public CartDto getCart(Long cartId){
//        return cartRepository.findById(cartId).stream().map((item)-> {
//                CartDto cart = new CartDto();
//                cart.setCartId(item.);
//                })
//                .orElseThrow(()-> new RuntimeException("invalid cart"));
//
//    }
}
