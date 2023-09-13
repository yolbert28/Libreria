package com.Progra1.Proyecto.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PurchaseDto {

    private String code;
    private String providerRif;
    private LocalDateTime date;
    private String comment;
    private Double total;
    private ProviderDto provider;

}
