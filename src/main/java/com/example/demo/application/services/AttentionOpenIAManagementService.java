package com.example.demo.application.services;

import com.example.demo.application.usecases.AttentionOpenIAService;
import com.example.demo.domain.model.Patient;
import com.example.demo.domain.port.PatientPersistencePort;
import com.example.demo.domain.request.AttentionPatientRequest;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttentionOpenIAManagementService implements AttentionOpenIAService {


    private final ChatClient chatClient;

    @Autowired
    PatientPersistencePort patientPersistencePort;

    public AttentionOpenIAManagementService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String getResponseAttention(AttentionPatientRequest attentionPatientRequest) {
        Patient patient = requestToModel(attentionPatientRequest);
        String responseAttentionPatient = chatClient.call(buildMessageForAI(patient));

        try {
            patientPersistencePort.create(patientToCreate(patient));
            return responseAttentionPatient;
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error creando el paciente en base de datos");
            return responseAttentionPatient;
        }
    }

    private String buildMessageForAI(Patient patient) {
        return "Soy un integrante de una brigada de emergencias de primeros auxilos de una universidad, " +
                "poseo conocimientos basicos de primeros auxilios y tengo un paciente que le ocurrio lo " +
                "siguiente y tiene la siguiente informacion:\n" +
                "\n" +
                "nombre: " + patient.getName() + "\n" +
                "carrera: " + patient.getCareer() + "\n" +
                "cedula: " + patient.getDocumentNumber() + "\n" +
                "edad: " + patient.getAge() + "\n" +
                "enfermedades: " + patient.getDiseases() + "\n" +
                "presion sistolica: " + patient.getSystolicPressure() + "\n" +
                "presion diastolica: " + patient.getDiastolicPressure() + "\n" +
                "nivel de azucar: " + patient.getSugarLevel() + "mg/dl\n" +
                "frecuencia cardiaca: " + patient.getHeartRate() + "latidos por minuto\n" +
                "oxigenacion en la sangre: " + patient.getHeartRate() + "%\n" +
                "Suceso: " + patient.getEventDescription() + "\n" +
                "\n" +
                "\n" +
                "¿Que deberia hacer? a mi disposicion tengo implementos de atencion de primero auxilos; " +
                "ferulas, gasas, guantes, solucion salina, cuellos ortopedicos, tigras, jabon antiseptico, " +
                "fosforos, vendas, venda elastica, paletas de madera(baja lenguas), algodon, comida. " +
                "La opcion de llamar a una ambulancia, llamar a un familia, llevarlo a urgencia yo mismo, " +
                "llamar a los bomberos. No puedo resetar o dar ningun tipo de medicamento, solo estoy " +
                "capacitado para ser un primer respondiente, esta prohibido dar medicamentos o aplicar " +
                "inyecciones.  A veces tenemos a disposicion un lugar de servicio medico en la universidad. " +
                "En caso de necesitar un vendaje dime que tipo de vendaje debo utilizar (Blando o contentivo, " +
                "Compresivo, Rígido, Circular, Espiral, En 8, Espiga, Velpeau, etc...). " +
                "Tambien contamos con la ayuda de un area protegida llamada EMI que es un servicio para la " +
                "atención de eventos de salud que se constituya como urgencia o emergencia médica.";
    }

    private Patient patientToCreate(Patient patient) {
        Long currentDate = System.currentTimeMillis();
        return patient.toBuilder()
                .creationDate(currentDate)
                .modificationDate(currentDate)
                .build();
    }


    private Patient requestToModel(AttentionPatientRequest attentionPatientRequest) {
        return Patient.builder()
                .name(attentionPatientRequest.getName())
                .career(attentionPatientRequest.getCareer())
                .documentNumber(attentionPatientRequest.getDocumentNumber())
                .age(attentionPatientRequest.getAge())
                .diseases(attentionPatientRequest.getDiseases())
                .systolicPressure(attentionPatientRequest.getSystolicPressure())
                .diastolicPressure(attentionPatientRequest.getDiastolicPressure())
                .sugarLevel(attentionPatientRequest.getSugarLevel())
                .heartRate(attentionPatientRequest.getHeartRate())
                .oxygenation(attentionPatientRequest.getOxygenation())
                .eventDescription(attentionPatientRequest.getEventDescription())
                .build();
    }


}
