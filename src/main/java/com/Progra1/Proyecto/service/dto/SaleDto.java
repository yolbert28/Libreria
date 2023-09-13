package com.Progra1.Proyecto.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaleDto {

    private String code;
    private String clientRif;
    private LocalDateTime date;
    private String comment;
    private Double total;
    private ClientDto client;

}
