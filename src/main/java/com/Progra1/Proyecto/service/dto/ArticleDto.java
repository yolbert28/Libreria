package com.Progra1.Proyecto.service.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto {

    private String code;
    private String codeDpto;
    private String description;
    private Double cost;
    private Double priceD;
    private Double priceM;
    private Integer existD;
    private Integer existM;
    private String status;
    private DepartmentDto department;

}