package com.gce.ba.homework.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gce.ba.homework.utils.enums.Currency;
import com.gce.ba.homework.utils.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payments_id", discriminatorType = DiscriminatorType.INTEGER)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Positive(message = "Amount required (positive decimal)!")
    private double amount;
    @NotNull(message = "Debtor_iban required!")
    private String debtor_iban;
    @NotNull(message = "Creditor_iban required!")
    private String creditor_iban;
    @CreationTimestamp
    private LocalDateTime creationdDateTime;
    @JsonIgnore
    private LocalDateTime cancelationDateTime;
    @JsonIgnore
    private boolean validity = true;
    @JsonIgnore
    private double cancelationFee;
    @JsonIgnore
    private boolean successfullyNotified;
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Currency cancelationFeeCurrency = Currency.EUR;

}
