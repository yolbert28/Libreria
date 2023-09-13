package com.Progra1.Proyecto.service;

import com.Progra1.Proyecto.persistence.Entity.Purchase;
import com.Progra1.Proyecto.service.dto.PurchaseDto;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {

    public PurchaseDto save(PurchaseDto purchaseDto);
    public PurchaseDto update(PurchaseDto purchaseDto);
    public Optional<PurchaseDto> findById(String code);
    public List<PurchaseDto> findAll();
    public List<PurchaseDto> findByProvider(String rif);
    public void delete(String code);

}
