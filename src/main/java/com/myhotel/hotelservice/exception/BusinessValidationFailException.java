package com.myhotel.hotelservice.exception;

public class BusinessValidationFailException extends RuntimeException {
    public BusinessValidationFailException(String message) {
        super(message);
    }
}
