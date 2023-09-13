package com.Progra1.Proyecto.persistence.Repository;

import com.Progra1.Proyecto.persistence.Entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client,String> {
    List<Client> findAllByStatus(String status);
}
