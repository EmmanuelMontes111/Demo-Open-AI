package com.example.demo.domain.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Patient {
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
