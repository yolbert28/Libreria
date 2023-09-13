package com.Progra1.Proyecto.service;

import com.Progra1.Proyecto.persistence.Entity.Provider;
import com.Progra1.Proyecto.service.dto.ProviderDto;

import java.util.List;
import java.util.Optional;

public interface ProviderService{
    public ProviderDto save(ProviderDto providerDto);
    public ProviderDto update(ProviderDto providerDto);
    public Optional<ProviderDto> findByRif(String rif);
    public List<ProviderDto> findAll();
    public void delete(String rif);

}
