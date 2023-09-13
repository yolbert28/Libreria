package com.Progra1.Proyecto.mapper;

import com.Progra1.Proyecto.persistence.Entity.Article;
import com.Progra1.Proyecto.service.dto.ArticleDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface ArticleMapper {

    @Mappings({
            @Mapping(target = "code",source = "code"),
            @Mapping(target = "codeDpto",source = "codeDpto"),
            @Mapping(target = "description",source = "description"),
            @Mapping(target = "cost",source = "cost"),
            @Mapping(target = "priceD",source = "priceD"),
            @Mapping(target = "priceM",source = "priceM"),
            @Mapping(target = "existD",source = "existD"),
            @Mapping(target = "existM",source = "existM"),
            @Mapping(target = "status",source = "status"),
            @Mapping(target = "department", source = "department")
    })
    ArticleDto articleToArticleDto(Article article);
    List<ArticleDto> articleToArticleDto(List<Article> article);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "articleSales",ignore = true),
            @Mapping(target = "articlePurchases",ignore = true)
    })
    Article articleDtoToArticle(ArticleDto articleDto);

}
