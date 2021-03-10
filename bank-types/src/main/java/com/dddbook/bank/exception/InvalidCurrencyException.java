package com.dddbook.bank.exception;

public class InvalidCurrencyException extends RuntimeException{

    public InvalidCurrencyException(String message) {
        super(message);
    }

    public InvalidCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCurrencyException() {

    }
}
