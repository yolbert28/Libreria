package com.Progra1.Proyecto.mapper;

import com.Progra1.Proyecto.persistence.Entity.Provider;
import com.Progra1.Proyecto.service.dto.ProviderDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProviderMapper {

    @Mappings({
            @Mapping(target = "rif",source = "rif"),
            @Mapping(target = "name",source = "name"),
            @Mapping(target = "direction",source = "direction"),
            @Mapping(target = "phone",source = "phone"),
            @Mapping(target = "email",source = "email")
    })
    ProviderDto providerToProviderDto(Provider provider);
    List<ProviderDto> providerToProviderDto(List<Provider> provider);

    @InheritInverseConfiguration
    @Mapping(target = "purchases",ignore = true)
    Provider providerDtoToProvider(ProviderDto providerDto);

}
