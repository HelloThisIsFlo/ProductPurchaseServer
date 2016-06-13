package com.professionalbeginner.domain.application.driven;

/**
 * Exception to be thrown when the provided Id is invalid
 */
public class InvalidIdException extends RuntimeException {

    public InvalidIdException() {
    }

    public InvalidIdException(String message) {
        super(message);
    }

    public InvalidIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidIdException(Throwable cause) {
        super(cause);
    }
}
