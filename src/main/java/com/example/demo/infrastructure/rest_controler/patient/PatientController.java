package com.example.demo.infrastructure.rest_controler.patient;

import com.example.demo.application.usecases.AttentionOpenIAService;
import com.example.demo.application.usecases.PatientService;
import com.example.demo.domain.dto.PatientDto;
import com.example.demo.domain.request.AttentionPatientRequest;
import com.example.demo.domain.responseRest.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @PutMapping("/update/{id}")
    public ResponseEntity<Response<PatientDto>> updatePatient(@PathVariable Long id, @RequestBody AttentionPatientRequest request) {
        return patientService.updatePatient(id, request);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<Response<PatientDto>> getPatient(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

    @GetMapping("/patients")
    public ResponseEntity<Response<List<PatientDto>>> getPatients(){
        return patientService.getAllPatients();
    }

    @DeleteMapping("/patient/delete/{id}")
    public ResponseEntity<Response> deletePatient(@PathVariable Long id) {
        return patientService.deletePatientById(id);
    }
}
