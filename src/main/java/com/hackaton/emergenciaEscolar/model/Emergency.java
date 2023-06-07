package com.hackaton.emergenciaEscolar.model;

import lombok.Data;

@Data
public class Emergency {
    private Double latitude;
    private Double longitude;
    private String subRegion;
    private String region;
    private String street;
    private String district;
    private String streetNumber;

}
