package com.example.demo.infrastructure.rest_controler.openai;

import com.example.demo.application.usecases.AttentionOpenIAService;
import com.example.demo.domain.request.AttentionPatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelperAttentionController {

    @Autowired
    AttentionOpenIAService attentionOpenIAService;

    @PostMapping("/patient")
    public String generateHelpBrigadier(@RequestBody AttentionPatientRequest request) {
        return attentionOpenIAService.getResponseAttention(request);
    }
}
