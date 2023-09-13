package com.Progra1.Proyecto.service;

import com.Progra1.Proyecto.service.dto.SaleDto;

import java.util.List;
import java.util.Optional;

public interface SaleService {

    public SaleDto save(SaleDto saleDto);
    public SaleDto update(SaleDto saleDto);
    public Optional<SaleDto> findById(String code);
    public List<SaleDto> findAll();
    public List<SaleDto> findByClient(String code);
    public void delete(String code);

}
