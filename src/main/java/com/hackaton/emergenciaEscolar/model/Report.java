package com.hackaton.emergenciaEscolar.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class Report {
    private String information;
    private List<MultipartFile> files;
}
