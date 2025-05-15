package dev.pndev.stock.exception;

public class DomainException extends RuntimeException {

    public enum ErrorType{
        PRODUCT_NOT_FOUND,
        PRODUCT_OUT_OF_STOCK,
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