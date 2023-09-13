package com.Progra1.Proyecto.mapper;

import com.Progra1.Proyecto.persistence.Entity.ArticleSale;
import com.Progra1.Proyecto.service.dto.ArticleSaleDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = {SaleMapper.class, ArticleSaleMapper.class})
public interface ArticleSaleMapper {

    @Mappings({
            @Mapping(target = "articleCode",source = "articleSalePK.articleCode"),
            @Mapping(target = "saleCode",source = "articleSalePK.saleCode"),
            @Mapping(target = "quantity",source = "quantity"),
            @Mapping(target = "amount",source = "amount"),
            @Mapping(target = "sale",source = "sale"),
            @Mapping(target = "article",source = "article")
    })
    ArticleSaleDto articleSaleToArticleSaleDto(ArticleSale articleSale);
    List<ArticleSaleDto> articleSaleToArticleSaleDto(List<ArticleSale> articleSale);

    @InheritInverseConfiguration
    ArticleSale articleSaleDtoToArticleSale(ArticleSaleDto articleSaleDto);

}
