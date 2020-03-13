package com.gce.ba.homework.service.paymentCancelationService;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.exceptions.CancelationNotAvailableException;
import com.gce.ba.homework.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class PaymentCancelationImpl implements PaymentCancelation {

    private final PaymentRepository paymentRepository;

    public PaymentCancelationImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment cancelPayment(Payment payment) throws CancelationNotAvailableException {
        if (isItPossibleToCancelPayment(payment.getCreationdDateTime())) {
            double cancelationFee = calculateCancelationFee(payment);
            payment.setCancelationFee(cancelationFee);
            payment.setCancelationDateTime(LocalDateTime.now());
            payment.setValidity(false);
            paymentRepository.save(payment);
            return payment;
        } else {
            throw new CancelationNotAvailableException("Payment is too long in the system!");
        }
    }

    private double calculateCancelationFee(Payment payment) {
        Duration paymentIsInSystem = Duration.between(LocalDateTime.now(), payment.getCreationdDateTime());
        return Math.abs(paymentIsInSystem.toHours()) * payment.getPaymentType().getCancelationFeeCoefficient();
    }

    private boolean isItPossibleToCancelPayment(LocalDateTime creationdDateTime) {
        return LocalDateTime.now().isBefore(creationdDateTime.with(LocalTime.MAX));
    }
}
