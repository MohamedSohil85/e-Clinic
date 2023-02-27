package com.example.clinic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class OpeningTimeDTO {
    private String day;
    @JsonFormat(pattern = "HH:mm a")
    private LocalTime startTime;
    @JsonFormat(pattern = "HH:mm a")
    private LocalTime endTime;
}
