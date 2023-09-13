package com.Progra1.Proyecto.service.implement;

import com.Progra1.Proyecto.exception.ResourceFoundException;
import com.Progra1.Proyecto.exception.ResourceIsBeingUsedException;
import com.Progra1.Proyecto.exception.ResourceNotFoundException;
import com.Progra1.Proyecto.mapper.ArticleMapper;
import com.Progra1.Proyecto.persistence.Entity.Article;
import com.Progra1.Proyecto.persistence.Entity.ArticlePurchase;
import com.Progra1.Proyecto.persistence.Entity.ArticleSale;
import com.Progra1.Proyecto.persistence.Repository.*;
import com.Progra1.Proyecto.service.ArticleService;
import com.Progra1.Proyecto.service.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImplement implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleSaleRepository articleSaleRepository;
    private final ArticlePurchaseRepository articlePurchaseRepository;
    private final ArticleMapper mapper;

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        Optional<Article> articleCheck = articleRepository.findById(articleDto.getCode());

        if(articleCheck.isPresent()){
            throw new ResourceFoundException("El artículos con código: " + articleDto.getCode() + ". Ya existe", HttpStatus.CONFLICT);
        }

        Article article = mapper.articleDtoToArticle(articleDto);

        return mapper.articleToArticleDto(articleRepository.save(article));
    }

    @Override
    public ArticleDto update(ArticleDto articleDto) {
        Optional<Article> articleCheck = articleRepository.findById(articleDto.getCode());

        if(articleCheck.isEmpty()){
            throw new ResourceNotFoundException("No existe el artículos con código: " + articleDto.getCode() + ". Ya existe", HttpStatus.NOT_FOUND);
        }

        Article article = mapper.articleDtoToArticle(articleDto);

        return mapper.articleToArticleDto(articleRepository.save(article));
    }

    @Override
    public Optional<ArticleDto> findById(String code) {

        return articleRepository.findById(code).map(mapper::articleToArticleDto);

    }

    @Override
    public List<ArticleDto> findByDepartment(String code) {
        return mapper.articleToArticleDto(articleRepository.findAllByCodeDpto(code));
    }

    @Override
    public List<ArticleDto> findAll() {
        return mapper.articleToArticleDto((List<Article>) articleRepository.findAll());
    }

    @Override
    public List<ArticleDto> findByStatus(String status) {
        return mapper.articleToArticleDto(articleRepository.findAllByStatus(status));
    }

    @Override
    public void delete(String code) {

        Optional<Article> articleCheck = articleRepository.findById(code);

        if(articleCheck.isEmpty()){
            throw new ResourceNotFoundException("No existe un artículo con código: " + code,HttpStatus.NOT_FOUND);
        }

        List<ArticleSale>  articleSalesCheck = articleSaleRepository.findByArticleSalePK_ArticleCode(code);

        if(!articleSalesCheck.isEmpty()){
            throw new ResourceIsBeingUsedException("El artículo se encuentra asignado a alguna venta",HttpStatus.CONFLICT);
        }

        List<ArticlePurchase>  articlePurchasesCheck = articlePurchaseRepository.findByArticlePurchasePK_ArticleCode(code);

        if(!articlePurchasesCheck.isEmpty()){
            throw new ResourceIsBeingUsedException("El artículo se encuentra asignado a alguna compra",HttpStatus.CONFLICT);
        }

        articleRepository.delete(articleCheck.get());

    }
}
