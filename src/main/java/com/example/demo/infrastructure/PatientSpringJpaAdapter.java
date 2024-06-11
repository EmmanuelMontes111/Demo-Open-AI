package com.example.demo.infrastructure;

import com.example.demo.domain.model.Patient;
import com.example.demo.domain.port.PatientPersistencePort;
import com.example.demo.infrastructure.adapter.entity.PatientEntity;
import com.example.demo.infrastructure.adapter.repository.PatientRepository;
import com.example.demo.infrastructure.rest_controler.ResExceptiont.DataPatientException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientSpringJpaAdapter implements PatientPersistencePort {


    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient create(Patient patient) {
        PatientEntity patientEntity = modelToEntity(patient);
        return entityToModel(patientRepository.save(patientEntity));
    }


    @Override
    public Patient getById(Long id) {
        Optional<PatientEntity> clientEntitySaved = patientRepository.findById(id);
        if (clientEntitySaved.isPresent()) {
            return entityToModel(clientEntitySaved.get());
        } else {
            throw new DataPatientException("El id del paciente no existe en la base de datos");
        }
    }

    @Override
    public List<Patient> getAll() {
        List<PatientEntity> patientEntities;
        List<Patient> patientModels = new ArrayList<>();

        patientEntities = patientRepository.findAll();
        for (PatientEntity clientEntity : patientEntities) {
            patientModels.add(entityToModel(clientEntity));
        }

        return patientModels;
    }


    @Override
    public void deletePatient(Patient client) {
        PatientEntity patientEntityToSave = modelToEntity(client);
        patientRepository.delete(patientEntityToSave);
    }


    private PatientEntity modelToEntity(Patient patientModel) {
        return PatientEntity.builder()
                .name(patientModel.getName())
                .career(patientModel.getCareer())
                .documentNumber(patientModel.getDocumentNumber())
                .age(patientModel.getAge())
                .diseases(patientModel.getDiseases())
                .systolicPressure(patientModel.getSystolicPressure())
                .diastolicPressure(patientModel.getDiastolicPressure())
                .sugarLevel(patientModel.getSugarLevel())
                .heartRate(patientModel.getHeartRate())
                .oxygenation(patientModel.getOxygenation() / 100)
                .eventDescription(patientModel.getEventDescription())
                .creationDate(patientModel.getCreationDate())
                .modificationDate(patientModel.getModificationDate())
                .build();
    }

    private Patient entityToModel(PatientEntity patientEntity) {
        return Patient.builder()
                .name(patientEntity.getName())
                .career(patientEntity.getCareer())
                .documentNumber(patientEntity.getDocumentNumber())
                .age(patientEntity.getAge())
                .diseases(patientEntity.getDiseases())
                .systolicPressure(patientEntity.getSystolicPressure())
                .diastolicPressure(patientEntity.getDiastolicPressure())
                .sugarLevel(patientEntity.getSugarLevel())
                .heartRate(patientEntity.getHeartRate())
                .oxygenation(patientEntity.getOxygenation() * 100)
                .eventDescription(patientEntity.getEventDescription())
                .creationDate(patientEntity.getCreationDate())
                .modificationDate(patientEntity.getModificationDate())
                .build();
    }

}
