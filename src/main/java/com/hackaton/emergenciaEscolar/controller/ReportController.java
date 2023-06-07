package com.hackaton.emergenciaEscolar.controller;

import com.hackaton.emergenciaEscolar.model.Report;
import com.hackaton.emergenciaEscolar.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/report")
    @ResponseStatus(HttpStatus.OK)
    public String sendReport(@ModelAttribute Report report) {
        return reportService.sendReportByEmailAndWhatsapp(report);
    }

}
