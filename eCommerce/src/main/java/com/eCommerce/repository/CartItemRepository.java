package com.eCommerce.repository;

import com.eCommerce.model.Cart_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<Cart_Item, Integer> {


    @Query("delete from Cart_Item ci where ci.cart.cartId = :cartId")
    void removeAllProductFromCart(@Param("cartId") Integer cartId);


    @Query("delete from Cart_Item ci where ci.cart.cartId = :cartId and ci.product.productId = :productId")
    void removeProductFromCart(@Param("cartId") Integer cartId,
                               @Param("productId") Integer productId);
}
