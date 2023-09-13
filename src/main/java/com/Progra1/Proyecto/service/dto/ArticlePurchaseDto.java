package com.Progra1.Proyecto.service.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticlePurchaseDto {

    private String articleCode;
    private String purchaseCode;
    private Integer quantity;
    private Double amount;
    private PurchaseDto purchase;
    private ArticleDto article;

}
