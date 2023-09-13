package com.Progra1.Proyecto.controller;

import com.Progra1.Proyecto.service.DepartmentService;
import com.Progra1.Proyecto.service.dto.DepartmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/{code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable(value = "code") String code){
        return departmentService.findById(code).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.save(departmentDto),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.update(departmentDto),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDto>> getAll(){
        return new ResponseEntity<>(departmentService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getStatus/{status}")
    public ResponseEntity<List<DepartmentDto>> getByStatus(@PathVariable(value = "status") String status){
        return new ResponseEntity<>(departmentService.findByStatus(status),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable(value = "code") String code){
        departmentService.delete(code);

        return ResponseEntity.ok().build();
    }
}
