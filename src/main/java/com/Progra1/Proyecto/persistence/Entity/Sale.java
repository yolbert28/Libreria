package com.Progra1.Proyecto.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "tventas")
public class Sale {

    @Id
    @Column(name = "VenCod",length = 6)
    private String code;

    @Column(name = "VenCliRIF",length = 15)
    private String clientRif;

    @Column(name = "VenFecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @Column(name = "VenComen",length = 400)
    private String comment;

    @Column(name = "VenTotal")
    private Double total;

    @ManyToOne()
    @JoinColumn(name = "VenCliRIF",insertable = false,updatable = false)
    private Client client;

    @OneToMany(mappedBy = "articleSalePK.saleCode",cascade = CascadeType.ALL)
    private List<ArticleSale> articleSales;

}
