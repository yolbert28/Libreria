package com.Progra1.Proyecto.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleSaleDto {

    private String articleCode;
    private String saleCode;
    private Integer quantity;
    private Double amount;
    private SaleDto sale;
    private ArticleDto article;

}
