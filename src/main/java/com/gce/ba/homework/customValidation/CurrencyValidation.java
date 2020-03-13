package com.gce.ba.homework.customValidation;


import com.gce.ba.homework.utils.enums.Currency;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrencyValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrencyValidation {
    Currency[] anyOf();

    String message() default "Currency should be of {anyOf}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
