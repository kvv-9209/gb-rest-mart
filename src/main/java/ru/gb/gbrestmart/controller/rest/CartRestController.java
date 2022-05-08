package ru.gb.hhh;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbrestmart.entity.Product;


import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartRestController {

    private final CartService cartService;

    @GetMapping
    public List<Cart> getCartList() {
        return cartService.findAll();
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable("cartId") Long id) {
        Cart cart;
        if (id != null) {
            cart = cartService.findById(id);
            if (cart != null) {
                return new ResponseEntity<>(cart, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@Validated @RequestBody Product product) {
        Cart addProduct = cartService.addProduct(product);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/api/v1/cart/" + addProduct.getId()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("cartId") Long id, @Validated @RequestBody Product product) {
        product.setId(id);
        Cart addProduct = cartService.addProduct(product);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/api/v1/cart/" + addProduct.getId()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{cartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("cartId") Long id) {
        cartService.deleteByIdFromCart(id);
    }
}
