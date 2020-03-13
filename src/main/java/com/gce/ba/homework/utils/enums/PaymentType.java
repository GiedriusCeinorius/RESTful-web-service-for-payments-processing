package com.gce.ba.homework.utils.enums;

public enum PaymentType {
    TYPE1(0.05),
    TYPE2(0.1),
    TYPE3(0.15);

    PaymentType(double cancelationFeeCoefficient) {
        this.cancelationFeeCoefficient = cancelationFeeCoefficient;
    }

    private final double cancelationFeeCoefficient;

    public double getCancelationFeeCoefficient() {
        return cancelationFeeCoefficient;
    }
}
