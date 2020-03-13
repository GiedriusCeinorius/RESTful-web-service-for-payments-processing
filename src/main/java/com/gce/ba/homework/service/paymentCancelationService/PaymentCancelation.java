package com.gce.ba.homework.service.paymentCancelationService;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.exceptions.CancelationNotAvailableException;

public interface PaymentCancelation {

    Payment cancelPayment(Payment payment) throws CancelationNotAvailableException;
}
