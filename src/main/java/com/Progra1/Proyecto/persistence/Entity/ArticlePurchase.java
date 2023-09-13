package com.Progra1.Proyecto.persistence.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tarticulos_x_compras")
public class ArticlePurchase {

    @EmbeddedId
    private ArticlePurchasePK articlePurchasePK;

    @Column(name = "ACCant")
    private Integer quantity;

    @Column(name = "ACTotal")
    private Double amount;


    @ManyToOne()
    @JoinColumn(name = "ACComCod",insertable = false,updatable = false)
    private Purchase purchase;

    @ManyToOne()
    @JoinColumn(name = "ACArtCod",insertable = false,updatable = false)
    private Article article;
}
