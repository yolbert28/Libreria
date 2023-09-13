package com.Progra1.Proyecto.service;

import com.Progra1.Proyecto.persistence.Entity.Article;
import com.Progra1.Proyecto.service.dto.ArticleDto;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    public ArticleDto save(ArticleDto articleDto);
    public ArticleDto update(ArticleDto articleDto);
    public Optional<ArticleDto> findById(String code);
    public List<ArticleDto> findByDepartment(String code);
    public List<ArticleDto> findAll();
    public List<ArticleDto> findByStatus(String status);
    public void delete(String code);


}
