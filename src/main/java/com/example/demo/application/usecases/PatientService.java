package com.example.demo.application.usecases;

import com.example.demo.domain.dto.PatientDto;
import com.example.demo.domain.request.AttentionPatientRequest;
import com.example.demo.domain.responseRest.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {

    ResponseEntity<Response<PatientDto>> updatePatient(Long identificationNumber, AttentionPatientRequest attentionPatientRequest);

    ResponseEntity<Response<PatientDto>> getPatientById(Long identificationNumber);

    ResponseEntity<Response<List<PatientDto>>> getAllPatients();

    ResponseEntity<Response> deletePatientById(Long identificationNumber);

}
