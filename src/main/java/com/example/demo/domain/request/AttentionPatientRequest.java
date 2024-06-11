package com.example.demo.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Getter
public class AttentionPatientRequest implements Serializable {
    private String name;
    private String career;
    private Long documentNumber;
    private int age;
    private String diseases;
    private int systolicPressure;
    private int diastolicPressure;
    private int sugarLevel;
    private int heartRate;
    private int oxygenation;
    private String eventDescription;
}
