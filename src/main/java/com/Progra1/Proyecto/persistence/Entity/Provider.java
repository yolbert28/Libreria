package com.Progra1.Proyecto.persistence.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tproveedores")
public class Provider {

    @Id
    @Column(name = "ProRIF",length = 15)
    private String rif;

    @Column(name = "ProNom",length = 60)
    private String name;

    @Column(name = "ProDir",length = 60)
    private String direction;

    @Column(name = "ProTlf",length = 40)
    private String phone;

    @Column(name = "ProEmail")
    private String email;

    @OneToMany(mappedBy = "provider")
    private List<Purchase> purchases;

}
