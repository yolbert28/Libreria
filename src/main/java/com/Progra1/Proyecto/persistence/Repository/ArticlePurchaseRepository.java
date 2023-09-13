package com.Progra1.Proyecto.persistence.Repository;

import com.Progra1.Proyecto.persistence.Entity.ArticlePurchase;
import com.Progra1.Proyecto.persistence.Entity.ArticlePurchasePK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticlePurchaseRepository extends CrudRepository<ArticlePurchase,ArticlePurchasePK> {


    public List<ArticlePurchase> findByArticlePurchasePK_ArticleCode(String code);
    public List<ArticlePurchase> findByArticlePurchasePK_PurchaseCode(String code);
}
