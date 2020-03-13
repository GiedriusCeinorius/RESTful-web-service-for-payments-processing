package com.gce.ba.homework.service.paymentService;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.dto.CanceledPaymentDto;
import com.gce.ba.homework.utils.mapper.CanceledPaymentMapper;
import com.gce.ba.homework.exceptions.PaymentNotFoundException;
import com.gce.ba.homework.repository.PaymentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    private static final String NO_PAYMENT_WAS_FOUND = "No payment was found!";
    private final PaymentRepository paymentRepository;
    private final CanceledPaymentMapper canceledPaymentMapper;


    public CommonServiceImpl(PaymentRepository paymentRepository, CanceledPaymentMapper canceledPaymentMapper) {
        this.paymentRepository = paymentRepository;
        this.canceledPaymentMapper = canceledPaymentMapper;
    }

    @Override
    public List<Payment> getPayments(Pageable pageable) throws PaymentNotFoundException {
        List<Payment> validPayments = paymentRepository.findByValidity(true, pageable);
        if (validPayments.isEmpty()) {
            throw new PaymentNotFoundException(NO_PAYMENT_WAS_FOUND);
        } else {
            return validPayments;
        }
    }

    @Override
    public CanceledPaymentDto getCanceledPayment(Integer id) throws PaymentNotFoundException {
        Payment payment = paymentRepository.findByIdAndValidity(id, false).orElseThrow(() -> new PaymentNotFoundException(NO_PAYMENT_WAS_FOUND));
        return canceledPaymentMapper.toDto(payment);

    }

    @Override
    public Payment getPayment(Integer id) throws PaymentNotFoundException {
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(NO_PAYMENT_WAS_FOUND));
    }
}
