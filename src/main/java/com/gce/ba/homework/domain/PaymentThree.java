package com.gce.ba.homework.domain;

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
public class PaymentThree extends Payment {


    @NotNull(message = "Currency required!")
    @CurrencyValidation(anyOf = {Currency.EUR, Currency.USD})
    private String currency;
    @NotNull(message = "BIC_code required!")
    private String bic_code;

}
