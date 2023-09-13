package com.Progra1.Proyecto.controller;

import com.Progra1.Proyecto.service.SaleService;
import com.Progra1.Proyecto.service.dto.SaleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    @GetMapping("/{code}")
    public ResponseEntity<SaleDto> getSale(@PathVariable String code){
        return saleService.findById(code).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<SaleDto> saveSale(@RequestBody SaleDto sale){
        return new ResponseEntity<>(saleService.save(sale),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<SaleDto> updateSale(@RequestBody SaleDto sale){
        return new ResponseEntity<>(saleService.update(sale),HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<SaleDto> getAll(){
        return saleService.findAll();
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Void> deleteSale(@PathVariable String code){
        saleService.delete(code);

        return ResponseEntity.ok().build();

    }

}
