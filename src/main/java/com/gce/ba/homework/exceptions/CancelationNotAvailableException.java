package com.gce.ba.homework.exceptions;

public class CancelationNotAvailableException extends Exception {

    public CancelationNotAvailableException() {
        super();
    }

    public CancelationNotAvailableException(String message) {
        super(message);
    }

    public CancelationNotAvailableException(String message, Throwable couse) {
        super(message, couse);
    }

}
