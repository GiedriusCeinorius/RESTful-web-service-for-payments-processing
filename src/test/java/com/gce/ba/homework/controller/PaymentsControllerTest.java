package com.gce.ba.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.domain.PaymentOne;
import com.gce.ba.homework.dto.SpecificPayment;
import com.gce.ba.homework.service.paymentService.CommonService;
import com.gce.ba.homework.service.paymentService.PaymentRegistry;
import com.gce.ba.homework.service.paymentService.PaymentService;
import com.gce.ba.homework.utils.enums.Currency;
import com.gce.ba.homework.utils.enums.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


@WebMvcTest(PaymentsController.class)
class PaymentsControllerTest {

    @MockBean
    private CommonService commonService;
    @MockBean
    private PaymentRegistry paymentRegistry;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentService paymentService;

    private List<Payment> paymentList = new ArrayList<>();
    private SpecificPayment specificPayment;
    private PaymentOne payment;

    @BeforeEach
    void setup() {
        payment = new PaymentOne();
        payment.setId(1);
        payment.setPaymentType(PaymentType.TYPE1);
        payment.setAmount(23.23);
        payment.setDebtor_iban("AD1400080001001234567890");
        payment.setCreditor_iban("AT483200000012345864");
        payment.setDetails("Important details");
        paymentList.add(payment);

        specificPayment = new SpecificPayment();
        specificPayment.setId(2);
        specificPayment.setCancelationFee(45.45);
        specificPayment.setCancelationDateTime(LocalDateTime.now());
        specificPayment.setCancelationFeeCurrency(String.valueOf(Currency.EUR));
    }


    @Test
    void cancelPayment() throws Exception {
        when(commonService.getPayment(any(Integer.class))).thenReturn(payment);
        when(paymentRegistry.getService(any(PaymentType.class))).thenReturn(paymentService);
        when(paymentService.recallPayment(any(Payment.class))).thenReturn(specificPayment);

        mockMvc.perform(MockMvcRequestBuilders.delete("/payments/2"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(specificPayment)))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.cancelationFee", is(45.45)))
                .andDo(print())
                .andReturn();
    }


    @Test
    void getValidPayments() throws Exception {
        when(commonService.getPayments(any(Pageable.class))).thenReturn(paymentList);

        mockMvc.perform(MockMvcRequestBuilders.get("/payments/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(paymentList)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].amount", is(23.23)))
                .andDo(print())
                .andReturn();
    }

    @Test
    void getCanceledPayment() throws Exception {
        when(commonService.getSpecificPaymentInfo(any(Integer.class))).thenReturn(specificPayment);

        mockMvc.perform(MockMvcRequestBuilders.get("/payments/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(specificPayment)))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.cancelationFee", is(45.45)))
                .andDo(print())
                .andReturn();
    }
}