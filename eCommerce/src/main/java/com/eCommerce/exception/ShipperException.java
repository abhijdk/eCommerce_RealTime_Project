package com.eCommerce.exception;


public class ShipperException extends RuntimeException {
    public ShipperException() {

    }

    public ShipperException(String exMsg) {
        super(exMsg);
    }

}
