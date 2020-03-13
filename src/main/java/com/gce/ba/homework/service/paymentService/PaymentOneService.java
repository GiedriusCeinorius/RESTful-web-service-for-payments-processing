package com.gce.ba.homework.service.paymentService;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.dto.SpecificPayment;
import com.gce.ba.homework.exceptions.CancelationNotAvailableException;
import com.gce.ba.homework.repository.PaymentRepository;
import com.gce.ba.homework.service.notificationService.PaymentEvent;
import com.gce.ba.homework.service.paymentCancelationService.PaymentCancelation;
import com.gce.ba.homework.utils.enums.EventType;
import com.gce.ba.homework.utils.enums.PaymentType;
import com.gce.ba.homework.utils.mapper.CanceledPaymentMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service("TYPE1")
public class PaymentOneService implements PaymentService, ApplicationEventPublisherAware {

    private final PaymentRepository paymentRepository;
    private final PaymentCancelation paymentCancelation;
    private ApplicationEventPublisher publisher;
    private final CanceledPaymentMapper canceledPaymentMapper;

    public PaymentOneService(PaymentRepository paymentRepository, PaymentCancelation paymentCancelation, CanceledPaymentMapper canceledPaymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentCancelation = paymentCancelation;
        this.canceledPaymentMapper = canceledPaymentMapper;
    }

    @Override
    public Payment savePayment(Payment payment) {
        payment.setPaymentType(PaymentType.TYPE1);
        Payment savedPayment = paymentRepository.save(payment);
        publisher.publishEvent(new PaymentEvent(this, EventType.SAVED, payment));
        return savedPayment;
    }

    @Override
    public SpecificPayment recallPayment(Payment payment) throws CancelationNotAvailableException {
        Payment cancelPayment = paymentCancelation.cancelPayment(payment);
        publisher.publishEvent(new PaymentEvent(this, EventType.CANCELED, payment));
        return canceledPaymentMapper.toDto(cancelPayment);
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
