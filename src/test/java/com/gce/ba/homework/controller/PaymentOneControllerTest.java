package com.gce.ba.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.domain.PaymentOne;
import com.gce.ba.homework.service.paymentService.PaymentRegistry;
import com.gce.ba.homework.service.paymentService.PaymentService;
import com.gce.ba.homework.utils.enums.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PaymentOneController.class)
class PaymentOneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    PaymentRegistry paymentRegistry;


    @MockBean
    private PaymentService paymentService;

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

        when(paymentRegistry.getService(any(PaymentType.class))).thenReturn(paymentService);
        when(paymentService.savePayment(any(Payment.class))).thenReturn(payment);

    }


    @Test
    void successfullyCreatedPaymentTest() throws Exception {
        payment.setCurrency("EUR");

        mockMvc.perform(MockMvcRequestBuilders.post("/payments/TYPE1/")
                .content(objectMapper.writeValueAsString(payment))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void missingCurrencyTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/payments/TYPE1/")
                .content(objectMapper.writeValueAsString(payment))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errorDetails", is("Currency required!")))
                .andDo(print())
                .andReturn();
    }

    @Test
    void wrongCurrencyTest() throws Exception {
        payment.setCurrency("USD");

        mockMvc.perform(MockMvcRequestBuilders.post("/payments/TYPE1/")
                .content(objectMapper.writeValueAsString(payment))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errorDetails", is("Currency should be of [EUR]")))
                .andDo(print())
                .andReturn();
    }
}