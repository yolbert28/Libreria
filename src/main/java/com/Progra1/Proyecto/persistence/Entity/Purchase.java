package com.Progra1.Proyecto.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "tcompras")
public class Purchase {

    @Id
    @Column(name = "ComCod",length = 6)
    private String code;

    @Column(name = "ComProRIF",length = 15)
    private String providerRif;

    @Column(name = "ComFecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @Column(name = "ComComen",length = 400)
    private String comment;

    @Column(name = "ComTotal")
    private Double total;

    @ManyToOne()
    @JoinColumn(name = "ComProRIF",insertable = false,updatable = false)
    private Provider provider;

    @OneToMany(mappedBy = "articlePurchasePK.purchaseCode",cascade = CascadeType.ALL)
    private List<ArticlePurchase> articlePurchases;
}
