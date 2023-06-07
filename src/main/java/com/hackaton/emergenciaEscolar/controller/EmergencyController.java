package com.hackaton.emergenciaEscolar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hackaton.emergenciaEscolar.service.EmergencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EmergencyController {

    private final EmergencyService emergencyService;

    @PostMapping("/emergency")
    @ResponseStatus(HttpStatus.OK)
    public String sendEmergency(@RequestBody String json) throws JsonProcessingException {
        return emergencyService.transformJsonToEmergency(json);
    }
}
