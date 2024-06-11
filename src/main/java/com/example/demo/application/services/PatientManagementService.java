package com.example.demo.application.services;

import com.example.demo.application.usecases.PatientService;
import com.example.demo.domain.dto.PatientDto;
import com.example.demo.domain.model.Patient;
import com.example.demo.domain.port.PatientPersistencePort;
import com.example.demo.domain.request.AttentionPatientRequest;
import com.example.demo.domain.responseRest.Response;
import com.example.demo.infrastructure.rest_controler.ResExceptiont.DataPatientException;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PatientManagementService implements PatientService {

    @Autowired
    PatientPersistencePort patientPersistencePort;

    @Override
    public ResponseEntity<Response<PatientDto>> updatePatient(Long identificationNumber, AttentionPatientRequest attentionPatientRequest) {
        PatientDto patientFoundDto;
        Patient patientResponse;
        Patient patientFound;
        ResponseEntity<Response<PatientDto>> patientById = getPatientById(identificationNumber);

        patientFoundDto = Objects.requireNonNull(patientById.getBody()).getData();
        patientFound = dtoToModel(patientFoundDto);
        Patient patientToUpdate = requestToModel(attentionPatientRequest);

        patientFound = patientFound.toBuilder()
                .name(patientToUpdate.getName())
                .career(patientToUpdate.getCareer())
                .documentNumber(patientToUpdate.getDocumentNumber())
                .age(patientToUpdate.getAge())
                .diseases(patientToUpdate.getDiseases())
                .systolicPressure(patientToUpdate.getSystolicPressure())
                .diastolicPressure(patientToUpdate.getDiastolicPressure())
                .sugarLevel(patientToUpdate.getSugarLevel())
                .heartRate(patientToUpdate.getHeartRate())
                .oxygenation(patientToUpdate.getOxygenation())
                .eventDescription(patientToUpdate.getEventDescription())
                .modificationDate(System.currentTimeMillis())
                .build();

        try {
            patientResponse = patientPersistencePort.update(identificationNumber,patientFound);
        } catch (Exception ex) {
            throw new PersistenceException("A ocurrido un error actualizando el paciente en base de datos", ex);
        }

        PatientDto patientDto = modelToDto(patientResponse);
        Response<PatientDto> response = new Response<>(200, patientDto, "Paciente actualizado exitosamente", "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<PatientDto>> getPatientById(Long identificationNumber) {
        Patient patient;
        try {
            patient = patientPersistencePort.getById(identificationNumber);
        } catch (Exception ex) {
            if (ex instanceof DataPatientException) {
                throw ex;
            }
            throw new PersistenceException("A ocurrido un error en base de datos obteniendo los  datos ", ex);
        }
        PatientDto patientDto = modelToDto(patient);
        Response response = new Response<>(200, patientDto, "Informacion del patiente", "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Response<List<PatientDto>>> getAllPatients() {
        List<Patient> patients;
        List<PatientDto> patientDtos = new ArrayList<>();

        try {
            patients = patientPersistencePort.getAll();
        } catch (Exception ex) {
            throw new PersistenceException("A ocurrido un error en base de datos obteniendo los  datos", ex);
        }

        for (Patient patient : patients) {
            patientDtos.add(modelToDto(patient));
        }

        Response<List<PatientDto>> response = getResponse(patientDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Response<List<PatientDto>> getResponse(List<PatientDto> patientDtos) {
        Response<List<PatientDto>> response;
        if (patientDtos.isEmpty()) {
            response = new Response<>(200, null, "No hay pacientes en la base de datos", "");
        } else {
            response = new Response<>(200, patientDtos, "Lista de pacientes atendidos", "");
        }
        return response;
    }

    @Override
    public ResponseEntity<Response> deletePatientById(Long identificationNumber) {
        PatientDto patientDto;
        Patient patientToDelete;
        ResponseEntity<Response<PatientDto>> patientRequest = getPatientById(identificationNumber);
        patientDto = Objects.requireNonNull(patientRequest.getBody()).getData();
        patientToDelete = dtoToModel(patientDto);
        try {
            patientPersistencePort.deletePatient(identificationNumber, patientToDelete);
        } catch (Exception ex) {
            throw new PersistenceException("A ocurrido un error eliminando el paciente", ex);
        }
        Response<PatientDto> response = new Response<>(204, null, "Pasiente eliminado exitosamente", "");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    private PatientDto modelToDto(Patient patientModel) {
        return PatientDto.builder()
                .name(patientModel.getName())
                .career(patientModel.getCareer())
                .documentNumber(patientModel.getDocumentNumber())
                .age(patientModel.getAge())
                .diseases(patientModel.getDiseases())
                .systolicPressure(patientModel.getSystolicPressure())
                .diastolicPressure(patientModel.getDiastolicPressure())
                .sugarLevel(patientModel.getSugarLevel())
                .heartRate(patientModel.getHeartRate())
                .oxygenation(patientModel.getOxygenation())
                .eventDescription(patientModel.getEventDescription())
                .creationDate(patientModel.getCreationDate())
                .modificationDate(patientModel.getModificationDate())
                .build();
    }

    private Patient dtoToModel(PatientDto patientDto) {
        return Patient.builder()
                .name(patientDto.getName())
                .career(patientDto.getCareer())
                .documentNumber(patientDto.getDocumentNumber())
                .age(patientDto.getAge())
                .diseases(patientDto.getDiseases())
                .systolicPressure(patientDto.getSystolicPressure())
                .diastolicPressure(patientDto.getDiastolicPressure())
                .sugarLevel(patientDto.getSugarLevel())
                .heartRate(patientDto.getHeartRate())
                .oxygenation(patientDto.getOxygenation())
                .eventDescription(patientDto.getEventDescription())
                .creationDate(patientDto.getCreationDate())
                .modificationDate(patientDto.getModificationDate())
                .build();
    }

    private Patient requestToModel(AttentionPatientRequest request) {
        return Patient.builder()
                .name(request.getName())
                .career(request.getCareer())
                .documentNumber(request.getDocumentNumber())
                .age(request.getAge())
                .diseases(request.getDiseases())
                .systolicPressure(request.getSystolicPressure())
                .diastolicPressure(request.getDiastolicPressure())
                .sugarLevel(request.getSugarLevel())
                .heartRate(request.getHeartRate())
                .oxygenation(request.getOxygenation())
                .eventDescription(request.getEventDescription())
                .build();
    }
}
