package com.gce.ba.homework.utils.mapper;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.dto.SpecificPayment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CanceledPaymentMapper {
    SpecificPayment toDto(Payment payment);
}
