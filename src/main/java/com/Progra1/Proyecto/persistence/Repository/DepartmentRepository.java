package com.Progra1.Proyecto.persistence.Repository;

import com.Progra1.Proyecto.persistence.Entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department,String> {

    List<Department> findAllByStatus(String status);

}
