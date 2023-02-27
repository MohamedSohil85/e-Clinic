package com.example.clinic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;
    private double amount;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate paymentDate;
    private String cardNumber;
    private String cardholderName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date expirationDate;
    @OneToOne
    private Patient patient;
}
