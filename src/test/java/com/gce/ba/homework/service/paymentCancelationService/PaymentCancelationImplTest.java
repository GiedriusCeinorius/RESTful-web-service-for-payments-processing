package com.gce.ba.homework.service.paymentCancelationService;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.domain.PaymentOne;
import com.gce.ba.homework.exceptions.CancelationNotAvailableException;
import com.gce.ba.homework.repository.PaymentRepository;
import com.gce.ba.homework.utils.enums.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class PaymentCancelationImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentCancelationImpl paymentCancelation;

    private Payment payment;

    @BeforeEach
    void setUp() {

        payment = new PaymentOne();
        payment.setId(1);
        payment.setPaymentType(PaymentType.TYPE1);
        payment.setAmount(23.23);
        payment.setDebtor_iban("AD1400080001001234567890");
        payment.setCreditor_iban("AT483200000012345864");

    }

    @Test
    void cancelPaymentDateIsAfterLimitTest()  {
        payment.setCreationdDateTime(LocalDateTime.of(2019, 03, 13, 1, 32));
        assertThrows(CancelationNotAvailableException.class, () -> paymentCancelation.cancelPayment(this.payment));

    }

    @Test
    void cancelPaymentDateIsBeforeLimitTest()  {
        payment.setCreationdDateTime(LocalDateTime.of(2021, 3, 13, 20, 15));
        assertDoesNotThrow(() -> paymentCancelation.cancelPayment(this.payment));

    }
}