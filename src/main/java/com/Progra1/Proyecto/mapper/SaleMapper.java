package com.Progra1.Proyecto.mapper;

import com.Progra1.Proyecto.persistence.Entity.Sale;
import com.Progra1.Proyecto.service.dto.SaleDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ClientMapper.class})
public interface SaleMapper {

    @Mappings({
            @Mapping(target = "code",source = "code"),
            @Mapping(target = "clientRif",source = "clientRif"),
            @Mapping(target = "date",source = "date"),
            @Mapping(target = "comment",source = "comment"),
            @Mapping(target = "total",source = "total"),
            @Mapping(target = "client",source = "client")
    })
    SaleDto saleToSaleDto(Sale sale);
    List<SaleDto> saleToSaleDto(List<Sale> sale);

    @InheritInverseConfiguration
    @Mapping(target = "articleSales",ignore = true)
    Sale saleDtoToSale(SaleDto saleDto);

}
