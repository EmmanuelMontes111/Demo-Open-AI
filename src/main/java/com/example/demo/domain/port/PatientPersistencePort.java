package com.example.demo.domain.port;

import com.example.demo.domain.model.Patient;

import java.util.List;

public interface PatientPersistencePort {

    Patient create(Patient client);

    Patient getById(Long id);

    List<Patient> getAll();

    void deletePatient(Patient client);

}
