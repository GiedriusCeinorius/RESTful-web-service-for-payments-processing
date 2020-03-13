package com.gce.ba.homework.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CurrencyValidator implements ConstraintValidator<CurrencyValidation, CharSequence> {
    private List<String> acceptedValues;


    @Override
    public void initialize(CurrencyValidation annotation) {
        acceptedValues = Stream.of(annotation.anyOf()).map(Enum::name).collect(Collectors.toList());

    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}
