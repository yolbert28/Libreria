package com.Progra1.Proyecto.controller;

import com.Progra1.Proyecto.persistence.Entity.ArticlePurchasePK;
import com.Progra1.Proyecto.service.*;
import com.Progra1.Proyecto.service.dto.ArticlePurchaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/articlePurchase")
public class ArticlePurchaseController {

    private final ArticlePurchaseService articlePurchaseService;
    private final PurchaseService purchaseService;

    @GetMapping("/get")
    public ResponseEntity<ArticlePurchaseDto> getArticlePurchase(@RequestBody ArticlePurchasePK articlePurchasePK){
        return articlePurchaseService.findById(articlePurchasePK).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<ArticlePurchaseDto> saveArticlePurchase(@RequestBody ArticlePurchaseDto articlePurchaseDto){
        return new ResponseEntity<>(articlePurchaseService.save(articlePurchaseDto),HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticlePurchaseDto>> getAll(){
        return new ResponseEntity<>(articlePurchaseService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getArticles/{codeAr}")
    public ResponseEntity<List<ArticlePurchaseDto>> getArticles(@PathVariable String codeAr){
        return new ResponseEntity<>(articlePurchaseService.findByIdArt(codeAr),HttpStatus.OK);
    }

    @GetMapping("/getPurchases/{codePu}")
    public ResponseEntity<List<ArticlePurchaseDto>> getPurchases(@PathVariable(value = "codePu") String codePu){
        return new ResponseEntity<>(articlePurchaseService.findByIdPurchase(codePu),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteArticlePurchase(@RequestBody ArticlePurchasePK articlePurchasePk){

        Optional<ArticlePurchaseDto> articlePurchase = articlePurchaseService.findById(articlePurchasePk);

        articlePurchase.ifPresent(articlePurchaseService::delete);

        List<ArticlePurchaseDto> articlePurchases = articlePurchaseService.findByIdPurchase(articlePurchasePk.getPurchaseCode());

        if(articlePurchases.isEmpty()){
            purchaseService.delete(articlePurchasePk.getPurchaseCode());
        }

        return ResponseEntity.ok().build();
    }

}
