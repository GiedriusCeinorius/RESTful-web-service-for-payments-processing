package com.gce.ba.homework.service.paymentService;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.dto.CanceledPaymentDto;
import com.gce.ba.homework.exceptions.PaymentNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonService {

    List<Payment> getPayments(Pageable pageable) throws PaymentNotFoundException;

    CanceledPaymentDto getCanceledPayment(Integer id) throws PaymentNotFoundException;

    Payment getPayment(Integer id) throws PaymentNotFoundException;


}
