package com.gce.ba.homework.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SpecificPayment {
    private int id;
    private double cancelationFee;
    private String cancelationFeeCurrency;
    private LocalDateTime cancelationDateTime;
}
