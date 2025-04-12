package org.mrstm.quoraapi.exceptions;

public class CannotBeEmptyException extends RuntimeException {
    public CannotBeEmptyException(String message) {
        super(message);
    }
}
