package com.gce.ba.homework.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String errorDetails;

    public CustomErrorDetails(LocalDateTime timestamp, String message, String errorDetails) {
        this.timestamp = timestamp;
        this.message = message;
        this.errorDetails = errorDetails;
    }
}
