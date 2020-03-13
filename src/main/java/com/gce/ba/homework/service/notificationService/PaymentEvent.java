package com.gce.ba.homework.service.notificationService;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.utils.enums.EventType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class PaymentEvent extends ApplicationEvent {

    private EventType eventType;
    private Payment payment;

    public PaymentEvent(Object source, EventType eventType, Payment payment) {
        super(source);
        this.eventType = eventType;
        this.payment = payment;
    }
}
