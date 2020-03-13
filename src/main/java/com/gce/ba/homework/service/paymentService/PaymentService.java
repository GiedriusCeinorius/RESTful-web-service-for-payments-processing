package com.gce.ba.homework.service.paymentService;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.dto.CanceledPaymentDto;
import com.gce.ba.homework.exceptions.CancelationNotAvailableException;
import com.gce.ba.homework.exceptions.PaymentNotFoundException;

public interface PaymentService {

    Payment savePayment(Payment payment);

    CanceledPaymentDto recallPayment(Payment payment) throws PaymentNotFoundException, CancelationNotAvailableException;

}
