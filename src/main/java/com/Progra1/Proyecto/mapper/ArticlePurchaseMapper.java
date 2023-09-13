package com.Progra1.Proyecto.mapper;

import com.Progra1.Proyecto.persistence.Entity.ArticlePurchase;
import com.Progra1.Proyecto.service.dto.ArticlePurchaseDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ArticleMapper.class, PurchaseMapper.class})
public interface ArticlePurchaseMapper {

    @Mappings({
            @Mapping(target = "articleCode",source = "articlePurchasePK.articleCode"),
            @Mapping(target = "purchaseCode",source = "articlePurchasePK.purchaseCode"),
            @Mapping(target = "quantity",source = "quantity"),
            @Mapping(target = "amount",source = "amount"),
            @Mapping(target = "purchase",source = "purchase"),
            @Mapping(target = "article",source = "article")
    })
    ArticlePurchaseDto articlePurchaseToArticlePurchaseDto(ArticlePurchase articlePurchase);
    List<ArticlePurchaseDto> articlePurchaseToArticlePurchaseDto(List<ArticlePurchase> articlePurchase);

    @InheritInverseConfiguration
    ArticlePurchase articlePurchaseDtoToArticlePurchase(ArticlePurchaseDto articlePurchaseDto);

}
