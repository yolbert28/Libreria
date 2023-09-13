package com.Progra1.Proyecto.persistence.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tarticulos_x_ventas")
public class ArticleSale {

    @EmbeddedId
    private ArticleSalePK articleSalePK;

    @Column(name = "AVCant")
    private Integer quantity;

    @Column(name = "AVTotal")
    private Double amount;

    @ManyToOne()
    @JoinColumn(name = "AVVenCod",insertable = false,updatable = false)
    private Sale sale;

    @ManyToOne()
    @JoinColumn(name = "AVArtCod",insertable = false,updatable = false)
    private Article article;
}
