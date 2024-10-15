package com.example.exchange_pattaya.exception;

public class CbrException extends Exception {

    public CbrException(String message) {
        super(message);
    }

    public CbrException(String message, Throwable cause) {
        super(message, cause);
    }
}