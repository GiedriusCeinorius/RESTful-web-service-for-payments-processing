package com.gce.ba.homework.bootstrap;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.domain.PaymentOne;
import com.gce.ba.homework.domain.PaymentThree;
import com.gce.ba.homework.domain.PaymentTwo;
import com.gce.ba.homework.repository.PaymentRepository;
import com.gce.ba.homework.utils.enums.PaymentType;
import lombok.Data;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class SampleDataInitializer {

    PaymentRepository paymentRepository;

    public SampleDataInitializer(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void Initialize() {


        List<Payment> paymentList = new ArrayList<>();

        Payment paymentOne = new PaymentOne();
        paymentOne.setPaymentType(PaymentType.TYPE1);
        paymentOne.setAmount(100.00);
        paymentOne.setDebtor_iban("BE71096123456769");
        paymentOne.setCreditor_iban("BE7109343324");
        paymentOne.setSuccessfullyNotified(true);
        ((PaymentOne) paymentOne).setCurrency("EUR");
        ((PaymentOne) paymentOne).setDetails("some details");

        Payment paymentTwo = new PaymentTwo();
        paymentTwo.setPaymentType(PaymentType.TYPE2);
        paymentTwo.setAmount(200.00);
        paymentTwo.setDebtor_iban("BE71096123456769");
        paymentTwo.setCreditor_iban("BE7109343324");
        paymentTwo.setSuccessfullyNotified(false);
        ((PaymentTwo) paymentTwo).setCurrency("USD");
        ((PaymentTwo) paymentTwo).setDetails("some more details");

        Payment paymentThree = new PaymentThree();
        paymentThree.setPaymentType(PaymentType.TYPE3);
        paymentThree.setAmount(5000.00);
        paymentThree.setDebtor_iban("BE714545456769");
        paymentThree.setCreditor_iban("BE7109343324");
        paymentThree.setSuccessfullyNotified(true);
        ((PaymentThree) paymentThree).setCurrency("EUR");
        ((PaymentThree) paymentThree).setBic_code("6538489");
        paymentThree.setValidity(false);
        paymentThree.setCancelationDateTime(LocalDateTime.now().minusHours(12L));
        paymentThree.setCancelationFee(11 * PaymentType.TYPE3.getCancelationFeeCoefficient());

        paymentList.add(paymentOne);
        paymentList.add(paymentTwo);
        paymentList.add(paymentThree);
        paymentRepository.saveAll(paymentList);


    }
}
