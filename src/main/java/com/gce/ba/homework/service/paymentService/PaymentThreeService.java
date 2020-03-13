package com.gce.ba.homework.service.paymentService;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.dto.CanceledPaymentDto;
import com.gce.ba.homework.exceptions.CancelationNotAvailableException;
import com.gce.ba.homework.repository.PaymentRepository;
import com.gce.ba.homework.service.paymentCancelationService.PaymentCancelation;
import com.gce.ba.homework.utils.enums.PaymentType;
import com.gce.ba.homework.utils.mapper.CanceledPaymentMapper;
import org.springframework.stereotype.Service;

@Service("TYPE3")
public class PaymentThreeService implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentCancelation paymentCancelation;
    private final CanceledPaymentMapper canceledPaymentMapper;

    public PaymentThreeService(PaymentRepository paymentRepository, PaymentCancelation paymentCancelation, CanceledPaymentMapper canceledPaymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentCancelation = paymentCancelation;
        this.canceledPaymentMapper = canceledPaymentMapper;
    }

    @Override
    public Payment savePayment(Payment payment) {
        payment.setPaymentType(PaymentType.TYPE3);
        return paymentRepository.save(payment);
    }

    @Override
    public CanceledPaymentDto recallPayment(Payment payment) throws CancelationNotAvailableException {
        Payment canceledPayment = paymentCancelation.cancelPayment(payment);
        return canceledPaymentMapper.toDto(canceledPayment);
    }

}
