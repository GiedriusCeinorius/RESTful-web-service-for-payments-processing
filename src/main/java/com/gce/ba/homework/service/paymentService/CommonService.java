package com.gce.ba.homework.service.paymentService;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.dto.SpecificPayment;
import com.gce.ba.homework.exceptions.PaymentNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonService {

    SpecificPayment getSpecificPaymentInfo(Integer id) throws PaymentNotFoundException;

    Payment getPayment(Integer id) throws PaymentNotFoundException;

    List<Payment> getValidPaymentsFilterByAmount(Double amount, Pageable pageable) throws PaymentNotFoundException;


}
