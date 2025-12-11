package com.eCommerce.exception;

public class AddressException extends RuntimeException{

    public AddressException() {
    }
    public AddressException(String msg) {
        super(msg);
    }
}
