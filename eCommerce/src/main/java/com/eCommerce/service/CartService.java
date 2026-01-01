package com.eCommerce.service;

import com.eCommerce.exception.CartException;
import com.eCommerce.model.Cart;

import java.util.List;

public interface CartService {

    public Cart addProductToCard(Integer user_Id,Integer product_Id) throws CartException;

    public Cart increaseProductQuantity(Integer user_Id,Integer product_Id) throws CartException;

    public Cart decreaseProductQuantity(Integer user_Id,Integer product_Id) throws CartException;

    public void removeProductFromCart(Integer user_Id,Integer product_Id) throws CartException;

    public void removeAllProductFromCart(Integer user_Id) throws CartException;

    public Cart getAllProduct(Integer user_Id) throws CartException;
}
