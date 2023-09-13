package com.Progra1.Proyecto.service.implement;

import com.Progra1.Proyecto.exception.ResourceFoundException;
import com.Progra1.Proyecto.exception.ResourceNotFoundException;
import com.Progra1.Proyecto.exception.UnexpectedErrorException;
import com.Progra1.Proyecto.mapper.ArticleSaleMapper;
import com.Progra1.Proyecto.persistence.Entity.Article;
import com.Progra1.Proyecto.persistence.Entity.ArticleSale;
import com.Progra1.Proyecto.persistence.Entity.ArticleSalePK;
import com.Progra1.Proyecto.persistence.Entity.Sale;
import com.Progra1.Proyecto.persistence.Repository.ArticleRepository;
import com.Progra1.Proyecto.persistence.Repository.ArticleSaleRepository;
import com.Progra1.Proyecto.persistence.Repository.SaleRepository;
import com.Progra1.Proyecto.service.ArticleSaleService;
import com.Progra1.Proyecto.service.dto.ArticleDto;
import com.Progra1.Proyecto.service.dto.ArticleSaleDto;
import com.Progra1.Proyecto.service.dto.SaleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleSaleServiceImplement implements ArticleSaleService {


    private final ArticleSaleRepository articleSaleRepository;
    private final ArticleRepository articleRepository;
    private final SaleRepository saleRepository;
    private final ArticleSaleMapper mapper;

    @Override
    public ArticleSaleDto save(ArticleSaleDto articleSaleDto) {

        ArticleSalePK articleSalePK = new ArticleSalePK();
        articleSalePK.setArticleCode(articleSaleDto.getArticleCode());
        articleSalePK.setSaleCode(articleSaleDto.getSaleCode());

        Optional<ArticleSale> articleSale = this.findById(articleSalePK).map(mapper::articleSaleDtoToArticleSale);

        if(articleSale.isPresent()){
            throw new ResourceFoundException("El artículo ya existe en la venta",HttpStatus.CONFLICT);
        }

        Optional<Article> articleCheck = articleRepository.findById(articleSalePK.getArticleCode());
        Optional<Sale> saleCheck = saleRepository.findById(articleSalePK.getSaleCode());

        if(articleCheck.isEmpty()){

            throw new ResourceNotFoundException("El artículo con código: "+ articleSalePK.getArticleCode()
                    + " no existe",HttpStatus.NOT_FOUND);

        } else if(saleCheck.isEmpty()) {

            throw new ResourceNotFoundException("No existe la venta a la que desea asignarle el producto",HttpStatus.NOT_FOUND);

        } else if(articleSaleDto.getQuantity() >= 5 && articleCheck.get().getExistM() >= articleSaleDto.getQuantity()) {

            Sale sale = saleCheck.get();
            Article article = articleCheck.get();

            articleSaleDto.setAmount(article.getPriceM() * articleSaleDto.getQuantity());

            sale.setTotal(sale.getTotal() + (article.getPriceM() * articleSaleDto.getQuantity()));

            article.setExistM(article.getExistM() - articleSaleDto.getQuantity());

            articleRepository.save(article);

            saleRepository.save(sale);

            articleSaleRepository.save(mapper.articleSaleDtoToArticleSale(articleSaleDto));

            return articleSaleDto;

        }else if(articleSaleDto.getQuantity() < 5 && articleCheck.get().getExistD() >= articleSaleDto.getQuantity()){

            Sale sale = saleCheck.get();
            Article article = articleCheck.get();

            articleSaleDto.setAmount(article.getPriceD() * articleSaleDto.getQuantity());

            sale.setTotal(sale.getTotal() + (article.getPriceD() * articleSaleDto.getQuantity()));

            article.setExistD(article.getExistD() - articleSaleDto.getQuantity());

            articleRepository.save(article);

            saleRepository.save(sale);

            articleSaleRepository.save(mapper.articleSaleDtoToArticleSale(articleSaleDto));

            return articleSaleDto;

        }

        throw new UnexpectedErrorException("Error inesperado",HttpStatus.CONFLICT);

    }

    @Override
    public Optional<ArticleSaleDto> findById(ArticleSalePK articleSalePK) {
        Optional<ArticleSale> articleSale = articleSaleRepository.findById(articleSalePK);

        if(articleSale.isEmpty()){
            throw new ResourceNotFoundException("No existe el articulo con codigo:"+ articleSalePK.getArticleCode() +" en la venta con codigo:" + articleSalePK.getSaleCode(), HttpStatus.NOT_FOUND);
        }

        return articleSaleRepository.findById(articleSalePK).map(mapper::articleSaleToArticleSaleDto);
    }

    @Override
    public List<ArticleSaleDto> findByIdArt(String code) {
        return mapper.articleSaleToArticleSaleDto(articleSaleRepository.findByArticleSalePK_ArticleCode(code));
    }

    @Override
    public List<ArticleSaleDto> findByIdSale(String code) {
        return mapper.articleSaleToArticleSaleDto(articleSaleRepository.findByArticleSalePK_SaleCode(code));
    }

    @Override
    public List<ArticleSaleDto> findAll() {
        return mapper.articleSaleToArticleSaleDto((List<ArticleSale>) articleSaleRepository.findAll());
    }

    @Override
    public void delete(ArticleSaleDto articleSaleDto) {

        ArticleSale articleSale = mapper.articleSaleDtoToArticleSale(articleSaleDto);

        Optional<ArticleSale> articleSaleDtoCheck = articleSaleRepository.findById(articleSale.getArticleSalePK());

        if(articleSaleDtoCheck.isEmpty()){
            throw new ResourceNotFoundException("No existe el articulo en la venta",HttpStatus.NOT_FOUND);
        }

        Optional<Article> articleCheck = articleRepository.findById(articleSaleDto.getArticleCode());

        if(articleCheck.isEmpty()){
            throw new ResourceNotFoundException("No se encuentra registrado el artículo",HttpStatus.NOT_FOUND);
        }

        Article article = articleCheck.get();

        if((articleSale.getAmount()/articleSale.getQuantity()) == article.getPriceD()){
            article.setExistD(article.getExistD() + articleSale.getQuantity());
        }else {
            article.setExistM(article.getExistM() + articleSale.getQuantity());
        }

        Optional<Sale> saleCheck = saleRepository.findById(articleSaleDto.getSaleCode());


        if(saleCheck.isEmpty()){
            throw new ResourceNotFoundException("No se encuentra registrada la venta",HttpStatus.NOT_FOUND);
        }

        Sale sale = saleCheck.get();

        sale.setTotal(sale.getTotal() - articleSale.getAmount());
        saleRepository.save(sale);

        articleSaleRepository.delete(articleSale);

    }
}
