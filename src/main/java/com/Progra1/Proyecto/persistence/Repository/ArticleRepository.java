package com.Progra1.Proyecto.persistence.Repository;

import com.Progra1.Proyecto.persistence.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article,String> {

    List<Article> findAllByCodeDpto(String code);
    List<Article> findAllByStatus(String status);

}
