package com.example.clinic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OpeningTime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String day;
    @JsonFormat(pattern = "HH:mm a")
    private LocalTime startTime;
    @JsonFormat(pattern = "HH:mm a")
    private LocalTime endTime;
    @ManyToOne
    private Clinic clinic;
}
