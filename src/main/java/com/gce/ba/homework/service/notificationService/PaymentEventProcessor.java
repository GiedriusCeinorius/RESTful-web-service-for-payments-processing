package com.gce.ba.homework.service.notificationService;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class PaymentEventProcessor implements ApplicationListener<PaymentEvent> {

    private RestTemplate restTemplate;
    private PaymentRepository paymentRepository;

    public PaymentEventProcessor(RestTemplate restTemplate, PaymentRepository paymentRepository) {
        this.restTemplate = restTemplate;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void onApplicationEvent(PaymentEvent paymentEvent) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> notifyExternalService(paymentEvent));
        executor.shutdown();
    }

    private void notifyExternalService(PaymentEvent paymentEvent) {
        Payment payment = paymentEvent.getPayment();
        String url = SimpleWebServiceUrlFactory.getUrl(payment.getPaymentType());
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        List<HttpStatus> statuses = Stream.of(HttpStatus.values()).filter(code -> code.toString().startsWith("2")).collect(Collectors.toList());
        boolean isNotified = statuses.contains(response.getStatusCode());
        log.info("External service notification status is " + String.valueOf(isNotified).toUpperCase() + " that payment type " + payment.getPaymentType() + " was successfully " + paymentEvent.getEventType());
        payment.setSuccessfullyNotified(statuses.contains(response.getStatusCode()));
        paymentRepository.save(payment);
    }
}
