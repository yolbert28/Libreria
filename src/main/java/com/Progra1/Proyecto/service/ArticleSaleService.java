package com.Progra1.Proyecto.service;

import com.Progra1.Proyecto.persistence.Entity.ArticleSale;
import com.Progra1.Proyecto.persistence.Entity.ArticleSalePK;
import com.Progra1.Proyecto.service.dto.ArticleSaleDto;

import java.util.List;
import java.util.Optional;

public interface ArticleSaleService {

    public ArticleSaleDto save(ArticleSaleDto articleSaleDto);
    public Optional<ArticleSaleDto> findById(ArticleSalePK articleSalePK);
    public List<ArticleSaleDto> findByIdArt(String code);
    public List<ArticleSaleDto> findByIdSale(String code);
    public List<ArticleSaleDto> findAll();
    public void delete(ArticleSaleDto articleSale);

}
