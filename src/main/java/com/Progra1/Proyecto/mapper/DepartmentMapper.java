package com.Progra1.Proyecto.mapper;

import com.Progra1.Proyecto.persistence.Entity.Department;
import com.Progra1.Proyecto.service.dto.DepartmentDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mappings({
            @Mapping(target = "code",source = "code"),
            @Mapping(target = "description",source = "description"),
            @Mapping(target = "status",source = "status"),
    })
    DepartmentDto departmentToDepartmentDto(Department department);
    List<DepartmentDto> departmentToDepartmentDto(List<Department> department);
    @InheritInverseConfiguration
    @Mapping(target = "articles",ignore = true)
    Department departmentDtoToDepartment(DepartmentDto departmentDto);

}
