package com.eCommerce.exception;


public class PaymentException extends RuntimeException {



    public PaymentException() {
    }


    public PaymentException(String exMsg) {
        super(exMsg);
    }
}
