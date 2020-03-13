package com.gce.ba.homework.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gce.ba.homework.customValidation.CurrencyValidation;
import com.gce.ba.homework.utils.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@JsonTypeName(value = "TYPE2")
public class PaymentTwo extends Payment {


    @NotNull(message = "Currency required!")
    @CurrencyValidation(anyOf = {Currency.USD})
    private String currency;
    private String details;
}

