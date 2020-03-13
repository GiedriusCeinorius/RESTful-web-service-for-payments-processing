package com.gce.ba.homework.service.notificationService;

import com.gce.ba.homework.utils.enums.PaymentType;
import org.springframework.stereotype.Component;

@Component
public class SimpleWebServiceUrlFactory {

    public static String getUrl(PaymentType paymentType) {
        String url;
        switch (paymentType) {
            case TYPE1:
                url = "http://numbersapi.com/#random/year";
                break;
            case TYPE2:
                url = "http://numbersapi.com/#random/math";
                break;
            default:
                url = null;
        }
        return url;
    }
}
