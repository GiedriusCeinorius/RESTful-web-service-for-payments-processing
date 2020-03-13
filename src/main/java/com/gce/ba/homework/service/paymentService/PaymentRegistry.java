package com.gce.ba.homework.service.paymentService;

import com.gce.ba.homework.utils.enums.PaymentType;

public interface PaymentRegistry {

    PaymentService getService(PaymentType serviceName);
}
