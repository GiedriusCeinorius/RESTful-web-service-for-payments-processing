package com.gce.ba.homework.exceptions;

import java.util.function.Supplier;

public class PaymentNotFoundException extends Exception  {
    public PaymentNotFoundException() {
        super();
    }

    public PaymentNotFoundException(String message) {
        super(message);
    }

    public PaymentNotFoundException(String message, Throwable couse) {
        super(message, couse);
    }

}
