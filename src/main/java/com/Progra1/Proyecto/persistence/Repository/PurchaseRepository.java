package com.Progra1.Proyecto.persistence.Repository;

import com.Progra1.Proyecto.persistence.Entity.Purchase;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase,String> {

    List<Purchase> findAllByProviderRif(String rif);

}
