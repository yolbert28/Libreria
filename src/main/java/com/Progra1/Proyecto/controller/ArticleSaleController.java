package com.Progra1.Proyecto.controller;

import com.Progra1.Proyecto.persistence.Entity.ArticleSalePK;
import com.Progra1.Proyecto.service.ArticleSaleService;
import com.Progra1.Proyecto.service.SaleService;
import com.Progra1.Proyecto.service.dto.ArticleSaleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/articleSale")
public class ArticleSaleController {

    private final ArticleSaleService articleSaleService;
    private final SaleService saleService;

    @GetMapping("/get")
    public ResponseEntity<ArticleSaleDto> getArticleSale(@RequestBody ArticleSalePK articleSalePK){
        return articleSaleService.findById(articleSalePK).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<ArticleSaleDto> saveArticleSale(@RequestBody ArticleSaleDto articleSaleDto){
        return new ResponseEntity<>(articleSaleService.save(articleSaleDto),HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleSaleDto>> getAll(){
        return new ResponseEntity<>(articleSaleService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getArticles/{codeAr}")
    public ResponseEntity<List<ArticleSaleDto>> getArticles(@PathVariable String codeAr){
        return new ResponseEntity<>(articleSaleService.findByIdArt(codeAr),HttpStatus.OK);
    }

    @GetMapping("/getSales/{codeSa}")
    public ResponseEntity<List<ArticleSaleDto>> getSales(@PathVariable String codeSa){
        return new ResponseEntity<>(articleSaleService.findByIdSale(codeSa),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteArticleSale(@RequestBody ArticleSalePK articleSalePk){

        if(articleSaleService.findByIdSale(articleSalePk.getSaleCode()).isEmpty())
            saleService.delete(articleSalePk.getSaleCode());

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/deleteSale/{code}")
    public ResponseEntity<Void> deleteSale(@PathVariable String code){

        List<ArticleSaleDto> articleSale = articleSaleService.findByIdSale(code);

        if(!articleSale.isEmpty()){
            articleSale.forEach((article) -> {

                ArticleSalePK articleSalePK = new ArticleSalePK();
                articleSalePK.setArticleCode(article.getArticleCode());
                articleSalePK.setSaleCode(article.getSaleCode());

                deleteArticleSale(articleSalePK);
            });
        }

        saleService.delete(code);

        return ResponseEntity.ok().build();

    }

}
