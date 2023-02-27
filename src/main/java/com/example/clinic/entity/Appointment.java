package com.example.clinic.entity;

import com.example.clinic.enumeration.AppointmentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate appointmentDate;
    @JsonFormat(pattern = "HH:mm a")
    private LocalTime appointmentTime;
    @Enumerated(EnumType.STRING)
    private AppointmentType reason;
    @ManyToOne
    private Patient patient;
}
