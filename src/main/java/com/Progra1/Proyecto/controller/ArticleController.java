package com.Progra1.Proyecto.controller;

import com.Progra1.Proyecto.service.ArticleService;
import com.Progra1.Proyecto.service.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/{code}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable(value = "code") String code){
        return articleService.findById(code).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<ArticleDto> saveArticle(@RequestBody ArticleDto articleDto){
        return new ResponseEntity<>(articleService.save(articleDto),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ArticleDto> updateArticle(@RequestBody ArticleDto article){
        return new ResponseEntity<>(articleService.update(article),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleDto>> getAll(){
        return new ResponseEntity<>(articleService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getStatus/{status}")
    public ResponseEntity<List<ArticleDto>> getByStatus(@PathVariable(value = "status") String status){
        return new ResponseEntity<>(articleService.findByStatus(status),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Void> deleteArticle(@PathVariable(value = "code") String code){
        articleService.delete(code);

        return ResponseEntity.ok().build();

    }

}
