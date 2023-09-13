package com.Progra1.Proyecto.persistence.Entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "tclientes")
public class Client {

    @Id
    @Column(name = "CliRIF",length = 15)
    private String rif;

    @Column(name = "CliNom",length = 40)
    private String name;

    @Column(name = "CliDir",length = 60)
    private String direction;

    @Column(name = "CliTlf",length = 15)
    private String phone;

    @Column(name = "CliEstatus",length = 1)
    private String status;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Sale> sales;

}
