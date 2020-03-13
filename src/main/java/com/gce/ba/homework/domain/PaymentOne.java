package com.gce.ba.homework.domain;

import com.gce.ba.homework.customValidation.CurrencyValidation;
import com.gce.ba.homework.utils.enums.Currency;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class PaymentOne extends Payment {

    @NotNull(message = "Currency required!")
    @CurrencyValidation(anyOf = {Currency.EUR})
    private String currency;
    @NotNull(message = "Details required!")
    private String details;
}

