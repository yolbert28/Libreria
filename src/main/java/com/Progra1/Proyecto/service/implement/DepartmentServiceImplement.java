package com.Progra1.Proyecto.service.implement;

import com.Progra1.Proyecto.exception.ResourceFoundException;
import com.Progra1.Proyecto.exception.ResourceIsBeingUsedException;
import com.Progra1.Proyecto.exception.ResourceNotFoundException;
import com.Progra1.Proyecto.mapper.DepartmentMapper;
import com.Progra1.Proyecto.persistence.Entity.Article;
import com.Progra1.Proyecto.persistence.Entity.Department;
import com.Progra1.Proyecto.persistence.Repository.ArticleRepository;
import com.Progra1.Proyecto.persistence.Repository.DepartmentRepository;
import com.Progra1.Proyecto.service.DepartmentService;
import com.Progra1.Proyecto.service.dto.DepartmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImplement implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final ArticleRepository articleRepository;
    private final DepartmentMapper mapper;

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {

        Optional<Department> departmentCheck = departmentRepository.findById(departmentDto.getCode());

        if(departmentCheck.isPresent()){
            throw new ResourceFoundException("Ya existe un departamento con el cógido: " + departmentDto.getCode(), HttpStatus.CONFLICT);
        }

        Department department = mapper.departmentDtoToDepartment(departmentDto);

        return mapper.departmentToDepartmentDto(departmentRepository.save(department));

    }

    @Override
    public DepartmentDto update(DepartmentDto departmentDto) {

        Optional<Department> departmentCheck = departmentRepository.findById(departmentDto.getCode());

        if(departmentCheck.isEmpty()){
            throw new ResourceNotFoundException("No existe el departamento con el cógido: " + departmentDto.getCode(), HttpStatus.NOT_FOUND);
        }

        Department department = mapper.departmentDtoToDepartment(departmentDto);

        return mapper.departmentToDepartmentDto(departmentRepository.save(department));

    }

    @Override
    public Optional<DepartmentDto> findById(String code) {
        return  departmentRepository.findById(code).map(mapper::departmentToDepartmentDto);
    }

    @Override
    public List<DepartmentDto> findAll() {
        return mapper.departmentToDepartmentDto((List<Department>) departmentRepository.findAll());
    }

    @Override
    public List<DepartmentDto> findByStatus(String status) {
        return mapper.departmentToDepartmentDto(departmentRepository.findAllByStatus(status));
    }

    @Override
    public void delete(String code) {
        Optional<Department> departmentCheck = departmentRepository.findById(code);

        if(departmentCheck.isEmpty()) {
            throw new ResourceNotFoundException("No existe el departamento con código: " + code ,HttpStatus.NOT_FOUND);
        }

        List<Article> articleCheck = articleRepository.findAllByCodeDpto(code);

        if(!articleCheck.isEmpty()){
            throw new ResourceIsBeingUsedException("El departamento tiene artículos a su cargo",HttpStatus.CONFLICT);
        }

        departmentRepository.delete(departmentCheck.get());

    }
}
