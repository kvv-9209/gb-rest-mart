package ru.gb.hhh;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbrestmart.entity.Product;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartDao cartDao;

    @Transactional(propagation = Propagation.NEVER, isolation = Isolation.DEFAULT)
    public long count() {
        System.out.println(cartDao.count());
        return cartDao.count();
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    public Cart addProduct(Product product) {
        if (products == null) {
            products = new HashSet<>();
        }
        return products.add(product);
    }


//    public Cart addProductCart(Cart cart) {
//        if (cart.getId() != null) {
//            Optional<Cart> cartFromDBOptional = cartDao.findById(cart.getId());
//            if (cartFromDBOptional.isPresent()) {
//                Cart cartFromDB = cartFromDBOptional.get();
//               cartFromDB.setStatus(cart.getStatus());
//                return cartDao.save(cartFromDB);
//            }
//        }
//        return cartDao.save(cart);
//    }

    public List<Cart> findAll() {
        return cartDao.findAll();
    }

    public void deleteByIdFromCart(Long id) {
        try {
            cartDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
        }
    }

    public Cart findById(Long id) {
        return cartDao.findById(id).orElse(null);
    }

}
