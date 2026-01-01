package com.eCommerce.exception;


public class ShippingException extends RuntimeException {
    public ShippingException() {

    }

    public ShippingException(String exMsg) {
        super(exMsg);
    }

}
