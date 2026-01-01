package com.eCommerce.service.impl;


import com.eCommerce.exception.CartException;
import com.eCommerce.model.Cart;
import com.eCommerce.model.Cart_Item;
import com.eCommerce.model.Product;
import com.eCommerce.model.User;
import com.eCommerce.repository.CartItemRepository;
import com.eCommerce.repository.CartRepository;
import com.eCommerce.repository.ProductRepository;
import com.eCommerce.repository.UserRepository;
import com.eCommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    private double caluculateCartTotal(List<Cart_Item> cartItems) {

        double total = 0.0;

        for (Cart_Item item : cartItems) {
            double price = item.getProduct().getPrice();
            int quantity = item.getQuantity();
            total += price * quantity;
        }

        return total;
    }


    @Override
    public Cart addProductToCard(Integer user_Id, Integer product_Id) throws CartException {

        Product exisProd=productRepository.findById(product_Id).orElseThrow(
                ()->new RuntimeException("Product Not Found"));

        User exisUser=userRepository.findById(user_Id).orElseThrow(
                ()->new RuntimeException("User Not Found"));

        if(exisUser.getCart()!=null) {

            //Already User has active Cart which has products , now we add the product
            Cart userCart=exisUser.getCart();

            List<Cart_Item> cartItems=userCart.getCart_items();
            if(cartItems!=null) {
                for(int i=0;i<=cartItems.size();i++) {
                    if((cartItems.get(i).getProduct().getProduct_Id() == product_Id )
                            && (cartItems.get(i).getCart().getCart_Id()==userCart.getCart_Id())) {
                        throw new CartException("Product Already Exists in the Cart");
                    }
                }
            }
            Cart_Item cartItem=new Cart_Item();
            cartItem.setProduct(exisProd);
            cartItem.setQuantity(1);
            cartItem.setCart(userCart);
            userCart.getCart_items().add(cartItem);


            userCart.setTotal_Amount(caluculateCartTotal(cartItems));
            userRepository.save(exisUser);
            return exisUser.getCart();




        } else {

            //User has not Active Products in the Cart..Adding new Product
            Cart newCart=new Cart();
            newCart.setUser(exisUser);
            exisUser.setCart(newCart);

            Cart_Item cartItem=new Cart_Item();
            cartItem.setProduct(exisProd);
            cartItem.setQuantity(1);

            newCart.getCart_items().add(cartItem);
            cartItem.setCart(newCart);
            newCart.setTotal_Amount(caluculateCartTotal(newCart.getCart_items()));
            userRepository.save(exisUser);
            return exisUser.getCart();



        }
    }

    @Override
    public Cart increaseProductQuantity(Integer user_Id, Integer product_Id) throws CartException {

        // Fetch User
        User user = userRepository.findById(user_Id)
                .orElseThrow(() -> new CartException("User not found"));

        // Fetch Cart
        Cart cart = user.getCart();
        if (cart == null) {
            throw new CartException("Cart not found for this user");
        }

        // Find Cart Item
        Cart_Item cartItem = cart.getCart_items()
                .stream()
                .filter(item -> item.getProduct().getProduct_Id().equals(product_Id))
                .findFirst()
                .orElseThrow(() -> new CartException("Product Not Found In Cart"));

        // Increase Quantity
        cartItem.setQuantity(cartItem.getQuantity() + 1);

        // Recalculate Total
        cart.setTotal_Amount(caluculateCartTotal(cart.getCart_items()));

        // Save cart (cascade = ALL will save cartItems)
        cartRepository.save(cart);

        return cart;
    }


    @Override
    public Cart decreaseProductQuantity(Integer user_Id, Integer product_Id) throws CartException {

        User user = userRepository.findById(user_Id).orElseThrow(() -> new CartException("User Not Found"));
        if(user.getCart()==null){
            throw new CartException("Cart Not exist");
        }

        Cart cart = user.getCart();
        List<Cart_Item> cartItems =cart.getCart_items();

        Cart_Item cartItemTOUpdate = cartItems.stream().filter(
                        item -> item.getProduct().getProduct_Id().equals(product_Id)
                                &&
                                item.getCart().getCart_Id().equals(cart.getCart_Id())
                ).findFirst()
                .orElseThrow(() -> new CartException("Cart Item not found"));

        Integer quantity = cartItemTOUpdate.getQuantity();
        if(quantity>1){
            cartItemTOUpdate.setQuantity(quantity-1);
        }else {
            cartItems.remove(cartItemTOUpdate);
        }

        cart.setCart_items(cartItems);
        cart.setTotal_Amount(caluculateCartTotal(cartItems));

        return cartRepository.save(cart);
    }

    @Override
    public void removeProductFromCart(Integer cart_Id, Integer product_Id) throws CartException {

        Cart cart = cartRepository.findById(cart_Id).orElseThrow(() -> new CartException("Cart Not FOund"));
        cartItemRepository.removeProductFromCart(cart_Id,product_Id);
        cart.setTotal_Amount(caluculateCartTotal(cart.getCart_items()));

        cartRepository.save(cart);
    }

    @Override
    public void removeAllProductFromCart(Integer user_Id) throws CartException {
        Cart exCart = cartRepository.findById(user_Id).orElseThrow(() -> new CartException("Cart not Found"));
        cartItemRepository.removeAllProductFromCart(user_Id);

        exCart.setTotal_Amount(0.0);

        cartRepository.save(exCart);
    }

    @Override
    public Cart getAllProduct(Integer user_Id) throws CartException {

        return cartRepository.findById(user_Id).orElseThrow(() -> new CartException("Cart not found"));
    }
}
