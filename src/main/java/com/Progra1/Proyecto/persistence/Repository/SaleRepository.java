package com.Progra1.Proyecto.persistence.Repository;

import com.Progra1.Proyecto.persistence.Entity.Client;
import com.Progra1.Proyecto.persistence.Entity.Sale;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaleRepository extends CrudRepository<Sale,String> {

    List<Sale> findAllByClientRif(String rif);

}
