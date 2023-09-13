package com.Progra1.Proyecto.persistence.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ArticlePurchasePK implements Serializable {

    @Column(name = "ACArtCod",length = 4)
    private String articleCode;

    @Column(name = "ACComCod",length = 6)
    private String purchaseCode;

}
