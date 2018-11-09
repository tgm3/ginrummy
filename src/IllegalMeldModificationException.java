package com.example;

public class IllegalMeldModificationException extends RuntimeException {
    IllegalMeldModificationException() {
        super();
    }

    protected IllegalMeldModificationException(String errorMessage) {
        super(errorMessage);
    }
}
