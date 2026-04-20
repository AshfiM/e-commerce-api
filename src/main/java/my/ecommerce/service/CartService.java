package my.ecommerce.service;

import my.ecommerce.dto.CartDto;
import my.ecommerce.dto.CartItemDto;
import my.ecommerce.models.CartEntity;
import my.ecommerce.models.CartItemEntity;
import my.ecommerce.models.ProductEntity;
import my.ecommerce.models.UserEntity;
import my.ecommerce.repository.CartItemRepository;
import my.ecommerce.repository.CartRepository;
import my.ecommerce.repository.ProductsRepository;
import my.ecommerce.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;
    private final CartItemRepository cartItemRepository;
    public CartService(CartRepository cartRepository, UserRepository userRepository,
                       ProductsRepository productsRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productsRepository = productsRepository;
        this.cartItemRepository = cartItemRepository;
    }


    public CartEntity getCart(String userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()-> new UsernameNotFoundException("invalid user name"));

        return cartRepository.findByUserId(user);
    }

    public CartDto cartEntityToCartDto(CartEntity cart) {
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getId());
        cartDto.setCartItems(cartItemEntityToCartItemDto(cart.getCartItems()));
        cartDto.setCartId(cart.getId());
        return cartDto;

    }

    public List<CartItemDto> cartItemEntityToCartItemDto(List<CartItemEntity> cartItemsList) {
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        cartItemsList.forEach(cartItemEntity -> {
            CartItemDto cartItemDto = new CartItemDto();
            cartItemDto.setQty(cartItemEntity.getQty());
            cartItemDto.setProductId(cartItemEntity.getProduct().getId());
            cartItemDtos.add(cartItemDto);
        });
        return cartItemDtos;
    }

    public CartDto addItemToCart(CartItemDto cartItemDto, String userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()-> new UsernameNotFoundException("invalid user name"));
        CartEntity cart = cartRepository.findByUserId(user);
        if (cart == null) {
            System.out.println("no cart");
            cart = new CartEntity();
            cart.setUserId(user);
            cart = cartRepository.save(cart);
            System.out.println(cart.getId());
        }
        //System.out.println(cartItemDto.getProductId());

        ProductEntity product = productsRepository.findById(cartItemDto.getProductId())
                .orElseThrow(()-> new RuntimeException("invalid product id"));
        //System.out.println(cart == null);
        Optional<CartItemEntity> cartItem = cartItemRepository.findByCartAndProduct(cart, product);
        if (cartItem.isPresent()) {
            cartItem.get().setQty(cartItem.get().getQty() + cartItemDto.getQty());
            cartItem.get().setCart(cart);
            cartItemRepository.save(cartItem.get());
        }
        else  {
            CartItemEntity newItem = new CartItemEntity();
            newItem.setQty(cartItemDto.getQty());
            newItem.setProduct(product);
            newItem.setCart(cart);
            cartItemRepository.save(newItem);
        }
        CartEntity newCart = cartRepository.save(cart);
        return cartEntityToCartDto(newCart);

    }

}
