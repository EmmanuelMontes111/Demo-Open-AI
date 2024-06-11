package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto {
    private String name;
    private String career;
    private Long documentNumber;
    private int age;
    private String diseases;
    private int systolicPressure;
    private int diastolicPressure;
    private int sugarLevel;
    private int heartRate;
    private double oxygenation;
    private String eventDescription;
    private Long creationDate;
    private Long modificationDate;
}
