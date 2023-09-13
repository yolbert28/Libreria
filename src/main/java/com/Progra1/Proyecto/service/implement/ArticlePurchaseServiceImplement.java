package com.Progra1.Proyecto.service.implement;

import com.Progra1.Proyecto.exception.ResourceFoundException;
import com.Progra1.Proyecto.exception.ResourceNotFoundException;
import com.Progra1.Proyecto.mapper.ArticlePurchaseMapper;
import com.Progra1.Proyecto.persistence.Entity.Article;
import com.Progra1.Proyecto.persistence.Entity.ArticlePurchase;
import com.Progra1.Proyecto.persistence.Entity.ArticlePurchasePK;
import com.Progra1.Proyecto.persistence.Entity.Purchase;
import com.Progra1.Proyecto.persistence.Repository.ArticlePurchaseRepository;
import com.Progra1.Proyecto.persistence.Repository.ArticleRepository;
import com.Progra1.Proyecto.persistence.Repository.PurchaseRepository;
import com.Progra1.Proyecto.service.ArticlePurchaseService;
import com.Progra1.Proyecto.service.dto.ArticleDto;
import com.Progra1.Proyecto.service.dto.ArticlePurchaseDto;
import com.Progra1.Proyecto.service.dto.PurchaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticlePurchaseServiceImplement implements ArticlePurchaseService {

    private final ArticlePurchaseRepository articlePurchaseRepository;
    private final PurchaseRepository purchaseRepository;
    private final ArticleRepository articleRepository;
    private final ArticlePurchaseMapper mapper;

    @Override
    public ArticlePurchaseDto save(ArticlePurchaseDto articlePurchaseDto) {

        ArticlePurchasePK articlePurchasePK = new ArticlePurchasePK();
        articlePurchasePK.setPurchaseCode(articlePurchaseDto.getPurchaseCode());
        articlePurchasePK.setArticleCode(articlePurchaseDto.getArticleCode());

        Optional<ArticlePurchase> articlePurchaseCheck = articlePurchaseRepository.findById(articlePurchasePK);

        if(articlePurchaseCheck.isPresent()){
            throw new ResourceFoundException("Ya está registrado el artículo en la compra", HttpStatus.CONFLICT);
        }

        Optional<Article> articleCheck = articleRepository.findById(articlePurchaseDto.getArticleCode());

        if(articleCheck.isEmpty()){
            throw new ResourceNotFoundException("No existe el artículo con código: "+ articlePurchaseDto.getArticleCode(),HttpStatus.NOT_FOUND);
        }

        Optional<Purchase> purchaseCheck = purchaseRepository.findById(articlePurchaseDto.getPurchaseCode());

        if(purchaseCheck.isEmpty()){
            throw new ResourceNotFoundException("No existe la compra a la que desea asignarle el producto",HttpStatus.NOT_FOUND);
        }

        Purchase purchase = purchaseCheck.get();
        Article article = articleCheck.get();

        if(articlePurchaseDto.getQuantity() % 2 == 0) {

            articlePurchaseDto.setAmount(article.getPriceM() * (articlePurchaseDto.getQuantity()/2));
            articlePurchaseDto.setAmount(article.getPriceD() * (articlePurchaseDto.getQuantity()/2));

            purchase.setTotal(purchase.getTotal() + (article.getPriceM() * (articlePurchaseDto.getQuantity()/2)));
            purchase.setTotal(purchase.getTotal() + (article.getPriceD() * (articlePurchaseDto.getQuantity()/2)));

            article.setExistM(article.getExistM() + (articlePurchaseDto.getQuantity()/2));
            article.setExistD(article.getExistD() + (articlePurchaseDto.getQuantity()/2));

        }else {

            articlePurchaseDto.setAmount(article.getPriceM() * ((articlePurchaseDto.getQuantity()/2) + 0.5));
            articlePurchaseDto.setAmount(article.getPriceD() * ((articlePurchaseDto.getQuantity()/2) - 0.5));

            purchase.setTotal(purchase.getTotal() + (article.getPriceM() * ((articlePurchaseDto.getQuantity()/2) + 0.5)));
            purchase.setTotal(purchase.getTotal() + (article.getPriceD() * ((articlePurchaseDto.getQuantity()/2) - 0.5)));

            article.setExistM(article.getExistM() + (int)(((float)articlePurchaseDto.getQuantity()/2) + 0.5 ));
            article.setExistD(article.getExistD() + (int)(((float)articlePurchaseDto.getQuantity()/2) - 0.5 ));

        }

        articleRepository.save(article);

        purchaseRepository.save(purchase);

        ArticlePurchase articlePurchase = mapper.articlePurchaseDtoToArticlePurchase(articlePurchaseDto);

        return mapper.articlePurchaseToArticlePurchaseDto(articlePurchaseRepository.save(articlePurchase));

    }

    @Override
    public Optional<ArticlePurchaseDto> findById(ArticlePurchasePK articlePurchasePK) {
        return articlePurchaseRepository.findById(articlePurchasePK).map(mapper::articlePurchaseToArticlePurchaseDto);
    }

    @Override
    public List<ArticlePurchaseDto> findByIdArt(String code) {
        return mapper.articlePurchaseToArticlePurchaseDto(articlePurchaseRepository.findByArticlePurchasePK_ArticleCode(code));
    }

    @Override
    public List<ArticlePurchaseDto> findByIdPurchase(String code) {
        return mapper.articlePurchaseToArticlePurchaseDto(articlePurchaseRepository.findByArticlePurchasePK_PurchaseCode(code));
    }

    @Override
    public List<ArticlePurchaseDto> findAll() {
        return mapper.articlePurchaseToArticlePurchaseDto((List<ArticlePurchase>) articlePurchaseRepository.findAll());
    }

    @Override
    public void delete(ArticlePurchaseDto articlePurchaseDto) {

        ArticlePurchasePK articlePurchasePK = new ArticlePurchasePK();
        articlePurchasePK.setArticleCode(articlePurchaseDto.getArticleCode());
        articlePurchasePK.setPurchaseCode(articlePurchaseDto.getPurchaseCode());

        Optional<ArticlePurchase> articlePurchaseCheck = articlePurchaseRepository.findById(articlePurchasePK);

        if(articlePurchaseCheck.isEmpty()){
            throw new ResourceNotFoundException("No existe el artículo con código: " + articlePurchasePK.getArticleCode() + ". En la compra con código: " + articlePurchasePK.getPurchaseCode(),HttpStatus.NOT_FOUND);
        }

        Optional<Article> articleCheck = articleRepository.findById(articlePurchasePK.getArticleCode());

        if (articleCheck.isEmpty()){
            throw new ResourceNotFoundException("No existe el artículos con código: " + articlePurchasePK.getArticleCode(),HttpStatus.NOT_FOUND);
        }

        Optional<Purchase> purchaseCheck = purchaseRepository.findById(articlePurchasePK.getPurchaseCode());

        if (purchaseCheck.isEmpty()){
            throw new ResourceNotFoundException("No existe la compra con código: " + articlePurchasePK.getPurchaseCode(),HttpStatus.NOT_FOUND);
        }

        Article article = articleCheck.get();

        if(articlePurchaseDto.getQuantity() % 2 == 0){
            article.setExistM(article.getExistM() - articlePurchaseDto.getQuantity()/2);
            article.setExistD(article.getExistD() - articlePurchaseDto.getQuantity()/2);
        }else {
            article.setExistM(article.getExistM() - (int)(((float)articlePurchaseDto.getQuantity()/2) + 0.5 ));
            article.setExistD(article.getExistD() - (int)(((float)articlePurchaseDto.getQuantity()/2) - 0.5 ));
        }

        Purchase purchase = purchaseCheck.get();
        purchase.setTotal(purchase.getTotal() - articlePurchaseDto.getAmount());

        purchaseRepository.save(purchase);

        articlePurchaseRepository.delete(mapper.articlePurchaseDtoToArticlePurchase(articlePurchaseDto));

    }
}
