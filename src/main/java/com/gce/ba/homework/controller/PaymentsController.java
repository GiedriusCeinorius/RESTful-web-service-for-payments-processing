package com.gce.ba.homework.controller;

import com.gce.ba.homework.domain.*;
import com.gce.ba.homework.dto.SpecificPayment;
import com.gce.ba.homework.exceptions.CancelationNotAvailableException;
import com.gce.ba.homework.exceptions.PaymentNotFoundException;
import com.gce.ba.homework.service.paymentService.CommonService;
import com.gce.ba.homework.service.paymentService.PaymentRegistry;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;


@RestController
@RequestMapping("/payments")
public class PaymentsController {

    private final CommonService commonService;
    private final PaymentRegistry paymentRegistry;

    public PaymentsController(CommonService commonService, PaymentRegistry paymentRegistry) {
        this.commonService = commonService;
        this.paymentRegistry = paymentRegistry;
    }


    @DeleteMapping("/{id}")
    public SpecificPayment cancelPayment(@PathVariable("id") Integer id, HttpServletRequest request) throws CancelationNotAvailableException, PaymentNotFoundException {
        Payment payment = commonService.getPayment(id);
        return paymentRegistry.getService(payment.getPaymentType()).recallPayment(payment);

    }


    @GetMapping({"", "/"})
    public List<Payment> getValidPayments(Pageable pageable, HttpServletRequest request) throws PaymentNotFoundException {
        return commonService.getPayments(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecificPayment> getSpecificPayment(@PathVariable("id") Integer id, HttpServletRequest request) throws PaymentNotFoundException {
        return new ResponseEntity<>(commonService.getSpecificPaymentInfo(id), HttpStatus.OK);
    }
}