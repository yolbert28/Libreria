package com.Progra1.Proyecto.service;

import com.Progra1.Proyecto.persistence.Entity.Department;
import com.Progra1.Proyecto.service.dto.DepartmentDto;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    public DepartmentDto save(DepartmentDto departmentDto);
    public DepartmentDto update(DepartmentDto departmentDto);
    public Optional<DepartmentDto> findById(String code);
    public List<DepartmentDto> findAll();
    public List<DepartmentDto> findByStatus(String status);
    public void delete(String code);

}
