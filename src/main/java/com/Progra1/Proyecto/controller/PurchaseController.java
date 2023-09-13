package com.Progra1.Proyecto.controller;

import com.Progra1.Proyecto.service.PurchaseService;
import com.Progra1.Proyecto.service.dto.PurchaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("/{code}")
    public ResponseEntity<PurchaseDto> getPurchase(@PathVariable(value = "code") String code){
        return purchaseService.findById(code).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<PurchaseDto> savePurchase(@RequestBody PurchaseDto purchaseDto){
        return new ResponseEntity<>(purchaseService.save(purchaseDto),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<PurchaseDto> updatePurchase(@RequestBody PurchaseDto purchaseDto){
        return new ResponseEntity<>(purchaseService.update(purchaseDto),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PurchaseDto>> getAll(){
        return new ResponseEntity<>(purchaseService.findAll(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Void> deletePurchase(@PathVariable(value = "code") String code){
        purchaseService.delete(code);

        return ResponseEntity.ok().build();

    }

}
