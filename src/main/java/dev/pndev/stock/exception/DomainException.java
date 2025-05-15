package dev.pndev.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DomainException extends RuntimeException {

    public enum ErrorType{
        PRODUCT_NOT_FOUND,
        PRODUCT_OUT_OF_STOCK,
        INVALID_PRODUCT_OPERATION
    }

    private final ErrorType errorType;

    public DomainException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}