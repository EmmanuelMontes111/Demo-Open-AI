package com.example.demo.application.usecases;

import com.example.demo.domain.request.AttentionPatientRequest;

public interface AttentionOpenIAService {

    String getResponseAttention(AttentionPatientRequest attentionPatientRequest);

}
