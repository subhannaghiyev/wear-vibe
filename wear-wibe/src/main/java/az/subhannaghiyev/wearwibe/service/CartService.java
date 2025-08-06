package az.subhannaghiyev.wearwibe.service;

import az.subhannaghiyev.wearwibe.dto.CartResponseDto;
import az.subhannaghiyev.wearwibe.entity.Cart;

public interface CartService {

    CartResponseDto getCartByUserId(Long userId);
    CartResponseDto addProductToCart(Long userId, Long productId, int quantity);
    CartResponseDto removeProductFromCart(Long userId, Long productId);
    CartResponseDto clearCart(Long userId);
    void incrementQuantity(Long userId, Long productId);
    void decrementQuantity(Long userId, Long productId);

}
