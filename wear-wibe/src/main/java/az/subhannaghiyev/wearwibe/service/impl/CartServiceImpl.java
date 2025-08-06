package az.subhannaghiyev.wearwibe.service.impl;

import az.subhannaghiyev.wearwibe.dto.CartResponseDto;
import az.subhannaghiyev.wearwibe.entity.Cart;
import az.subhannaghiyev.wearwibe.entity.CartItem;
import az.subhannaghiyev.wearwibe.entity.Product;
import az.subhannaghiyev.wearwibe.entity.User;
import az.subhannaghiyev.wearwibe.mapper.CartMapper;
import az.subhannaghiyev.wearwibe.repository.CartRepository;
import az.subhannaghiyev.wearwibe.repository.ProductRepository;
import az.subhannaghiyev.wearwibe.repository.UserRepository;
import az.subhannaghiyev.wearwibe.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;


    @Override
    public CartResponseDto getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
        return cartMapper.toDto(cart);
    }

    @Override
    public CartResponseDto addProductToCart(Long userId, Long productId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cart.getCartItems().add(newItem);
        }

        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDto(savedCart);
    }

    @Override
    public CartResponseDto removeProductFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user " + userId));

        cart.getCartItems().removeIf(item -> item.getProduct().getId().equals(productId));

        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDto(savedCart);
    }

    @Override
    public CartResponseDto clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user " + userId));

        cart.getCartItems().clear();

        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDto(savedCart);
    }

    @Override
    public void incrementQuantity(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user " + userId));

        Optional<CartItem> cartItemOpt = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (cartItemOpt.isPresent()) {
            CartItem cartItem = cartItemOpt.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found " + productId));
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(1);
            cart.getCartItems().add(newItem);
        }

        cartRepository.save(cart);
    }

    @Override
    public void decrementQuantity(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user " + userId));

        Optional<CartItem> cartItemOpt = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (cartItemOpt.isPresent()) {
            CartItem cartItem = cartItemOpt.get();
            int currentQty = cartItem.getQuantity();
            if (currentQty > 1) {
                cartItem.setQuantity(currentQty - 1);
            } else {
                cart.getCartItems().remove(cartItem);
            }
            cartRepository.save(cart);
        } else {
            throw new RuntimeException("Product not  found in cart");
        }
    }

}
