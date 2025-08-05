package az.subhannaghiyev.wearwibe.service;

import az.subhannaghiyev.wearwibe.entity.Cart;

public interface CartService {

    Cart getCartByUserId(Long userId);
    Cart addProductToCart(Long userId, Long productId, int quantity);
    Cart removeProductFromCart(Long userId, Long productId);
    Cart clearCart(Long userId);
    void incrementQuantity(Long userId, Long productId);
    void decrementQuantity(Long userId, Long productId);

}
