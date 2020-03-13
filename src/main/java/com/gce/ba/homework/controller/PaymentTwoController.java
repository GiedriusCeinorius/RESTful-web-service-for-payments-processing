package com.gce.ba.homework.controller;


import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.domain.PaymentTwo;
import com.gce.ba.homework.service.paymentService.PaymentRegistry;
import com.gce.ba.homework.utils.enums.PaymentType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentTwoController {

    private final PaymentRegistry paymentRegistry;

    public PaymentTwoController(PaymentRegistry paymentRegistry) {
        this.paymentRegistry = paymentRegistry;
    }

    @PostMapping({"/TYPE2", "/TYPE2/"})
    public Payment createPayment(@Valid @RequestBody PaymentTwo payment, HttpServletRequest request) {
        payment.setPaymentType(PaymentType.TYPE2);
        return paymentRegistry.getService(PaymentType.TYPE2).savePayment(payment);
    }
}
