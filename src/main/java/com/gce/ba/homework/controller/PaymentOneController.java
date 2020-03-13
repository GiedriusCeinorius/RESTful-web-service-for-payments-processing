package com.gce.ba.homework.controller;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.domain.PaymentOne;
import com.gce.ba.homework.service.paymentService.PaymentRegistry;
import com.gce.ba.homework.utils.enums.PaymentType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/payments")
public class PaymentOneController {

    private PaymentRegistry paymentRegistry;


    public PaymentOneController(PaymentRegistry paymentRegistry) {
        this.paymentRegistry = paymentRegistry;

    }

    @PostMapping({"/TYPE1", "/TYPE1/"})
    public Payment createPayment(@Valid @RequestBody PaymentOne payment, HttpServletRequest request)  {
           payment.setPaymentType(PaymentType.TYPE1);
            return paymentRegistry.getService(payment.getPaymentType()).savePayment(payment);
    }

    @GetMapping({"/TYPE12"})
    public int createPayment2(String po)  {
        System.out.println("fdfdsf");
        int pr= 67;
        return pr;
    }

}
