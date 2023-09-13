package com.Progra1.Proyecto.persistence.Entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ArticleSalePK implements Serializable {

    @Column(name = "AVArtCod",length = 4)
    private String articleCode;

    @Column(name = "AVVenCod",length = 6)
    private String saleCode;

}
