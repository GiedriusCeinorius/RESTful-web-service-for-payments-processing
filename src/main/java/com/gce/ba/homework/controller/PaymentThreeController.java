package com.gce.ba.homework.controller;


import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.domain.PaymentThree;
import com.gce.ba.homework.service.paymentService.PaymentRegistry;
import com.gce.ba.homework.utils.enums.PaymentType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentThreeController {

    private final PaymentRegistry paymentRegistry;


    public PaymentThreeController(PaymentRegistry paymentRegistry) {
        this.paymentRegistry = paymentRegistry;

    }

    @PostMapping({"/TYPE3", "/TYPE3/"})
    public Payment createPayment(@Valid @RequestBody PaymentThree payment, HttpServletRequest request) {
        payment.setPaymentType(PaymentType.TYPE3);
        return paymentRegistry.getService(PaymentType.TYPE3).savePayment(payment);
    }

}
