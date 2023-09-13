package com.Progra1.Proyecto.mapper;

import com.Progra1.Proyecto.persistence.Entity.Purchase;
import com.Progra1.Proyecto.service.dto.PurchaseDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProviderMapper.class})
public interface PurchaseMapper {

    @Mappings({
            @Mapping(target = "code",source = "code"),
            @Mapping(target = "providerRif",source = "providerRif"),
            @Mapping(target = "date",source = "date"),
            @Mapping(target = "comment",source = "comment"),
            @Mapping(target = "total",source = "total"),
            @Mapping(target = "provider",source = "provider"),
    })
    PurchaseDto purchaseToPurchaseDto(Purchase purchase);
    List<PurchaseDto> purchaseToPurchaseDto(List<Purchase> purchase);

    @InheritInverseConfiguration
    @Mapping(target = "articlePurchases",ignore = true)
    Purchase purchaseDtoToPurchase(PurchaseDto purchaseDto);

}
