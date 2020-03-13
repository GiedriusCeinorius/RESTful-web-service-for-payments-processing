package com.gce.ba.homework.utils.mapper;

import com.gce.ba.homework.domain.Payment;
import com.gce.ba.homework.dto.CanceledPaymentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CanceledPaymentMapper {
    CanceledPaymentDto toDto(Payment payment);
}
