package az.subhannaghiyev.wearwibe.controller;

import az.subhannaghiyev.wearwibe.dto.CartResponseDto;
import az.subhannaghiyev.wearwibe.entity.Cart;
import az.subhannaghiyev.wearwibe.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable Long userId) {
        CartResponseDto cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    // Səbətə məhsul əlavə et
    @PostMapping("/{userId}/add")
    public ResponseEntity<CartResponseDto> addProductToCart(
            @PathVariable Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        CartResponseDto updatedCart = cartService.addProductToCart(userId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    // Səbətdən məhsul çıxar
    @DeleteMapping("/{userId}/remove")
    public ResponseEntity<CartResponseDto> removeProductFromCart(
            @PathVariable Long userId,
            @RequestParam Long productId) {

        CartResponseDto updatedCart = cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok(updatedCart);
    }

    // Səbəti boşalt
    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<CartResponseDto> clearCart(@PathVariable Long userId) {
        CartResponseDto clearedCart = cartService.clearCart(userId);
        return ResponseEntity.ok(clearedCart);
    }

    @PostMapping("/{userId}/increment")
    public ResponseEntity<Void> incrementQuantity(@PathVariable Long userId,
                                                  @RequestParam Long productId) {
        cartService.incrementQuantity(userId, productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/decrement")
    public ResponseEntity<Void> decrementQuantity(@PathVariable Long userId,
                                                  @RequestParam Long productId) {
        cartService.decrementQuantity(userId, productId);
        return ResponseEntity.ok().build();
    }
}
