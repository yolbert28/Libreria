package com.Progra1.Proyecto.persistence.Repository;

import com.Progra1.Proyecto.persistence.Entity.ArticleSale;
import com.Progra1.Proyecto.persistence.Entity.ArticleSalePK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleSaleRepository extends CrudRepository<ArticleSale, ArticleSalePK> {

    public List<ArticleSale> findByArticleSalePK_ArticleCode(String code);
    public List<ArticleSale> findByArticleSalePK_SaleCode(String code);

}
