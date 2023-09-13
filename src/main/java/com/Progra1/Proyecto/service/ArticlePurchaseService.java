package com.Progra1.Proyecto.service;

import com.Progra1.Proyecto.persistence.Entity.ArticlePurchase;
import com.Progra1.Proyecto.persistence.Entity.ArticlePurchasePK;
import com.Progra1.Proyecto.service.dto.ArticlePurchaseDto;

import java.util.List;
import java.util.Optional;

public interface ArticlePurchaseService {
    public ArticlePurchaseDto save(ArticlePurchaseDto articlePurchaseDto);
    public Optional<ArticlePurchaseDto> findById(ArticlePurchasePK articlePurchasePK);
    public List<ArticlePurchaseDto> findByIdArt(String code);
    public List<ArticlePurchaseDto> findByIdPurchase(String code);
    public List<ArticlePurchaseDto> findAll();
    public void delete(ArticlePurchaseDto articlePurchase);

}
