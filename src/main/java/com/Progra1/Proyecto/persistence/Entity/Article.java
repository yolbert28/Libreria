package com.Progra1.Proyecto.persistence.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tarticulos")
public class Article {

    @Id
    @Column(name = "ArtCod",length = 4)
    private String code;

    @Column(name = "ArtCodpto",length = 2)
    private String codeDpto;

    @Column(name = "ArtDesc",length = 40)
    private String description;

    @Column(name = "ArtCosto")
    private Double cost;

    @Column(name = "ArtPrecioDetal")
    private Double priceD;

    @Column(name = "ArtPrecioMayor")
    private Double priceM;

    @Column(name = "ArtExistDetal")
    private Integer existD;

    @Column(name = "ArtExistMayor")
    private Integer existM;

    @Column(name = "ArtEstatus",length = 1)
    private String status;

    @ManyToOne()
    @JoinColumn(name = "ArtCodpto",insertable = false,updatable = false)
    private Department department;

    @OneToMany(mappedBy = "articleSalePK.articleCode",cascade = CascadeType.ALL)
    private List<ArticleSale> articleSales;

    @OneToMany(mappedBy = "articlePurchasePK.articleCode",cascade = CascadeType.ALL)
    private List<ArticlePurchase> articlePurchases;

}
