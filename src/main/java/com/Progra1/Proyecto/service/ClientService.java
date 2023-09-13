package com.Progra1.Proyecto.service;

import com.Progra1.Proyecto.persistence.Entity.Client;
import com.Progra1.Proyecto.service.dto.ClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    public ClientDto save(ClientDto clientDto);
    public ClientDto update(ClientDto clientDto);
    public Optional<ClientDto> findByRif(String rif);
    public List<ClientDto> findAll();
    public List<ClientDto> findByStatus(String status);
    public void delete(String rif);
}
