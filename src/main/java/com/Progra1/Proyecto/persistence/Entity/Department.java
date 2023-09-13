package com.Progra1.Proyecto.persistence.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tdepartamentos")
public class Department {

    @Id
    @Column(name = "DptoCod",length = 2)
    private String code;

    @Column(name = "DptoDesc",length = 30)
    private String description;

    @Column(name = "DptoEstatus",length = 1)
    private String status;

    @OneToMany(mappedBy = "department")
    private List<Article> articles;
}
