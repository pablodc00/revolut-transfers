package com.revolut.util;

public class BusinessException extends Exception {
    
    private static final long serialVersionUID = 8693259432173435048L;

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}
