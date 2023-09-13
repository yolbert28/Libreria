package com.Progra1.Proyecto.mapper;

import com.Progra1.Proyecto.persistence.Entity.Client;
import com.Progra1.Proyecto.service.dto.ClientDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mappings({
            @Mapping(target = "rif",source = "rif"),
            @Mapping(target = "name",source = "name"),
            @Mapping(target = "direction",source = "direction"),
            @Mapping(target = "phone",source = "phone"),
            @Mapping(target = "status",source = "status")
    })
    ClientDto clientToClientDto(Client client);
    List<ClientDto> clientToClientDto(List<Client> clients);

    @InheritInverseConfiguration
    @Mapping(target = "sales",ignore = true)
    Client clientDtoToClient(ClientDto clientDto);

}
