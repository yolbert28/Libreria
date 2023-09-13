package com.Progra1.Proyecto.service.implement;

import com.Progra1.Proyecto.exception.ResourceFoundException;
import com.Progra1.Proyecto.exception.ResourceIsBeingUsedException;
import com.Progra1.Proyecto.exception.ResourceNotFoundException;
import com.Progra1.Proyecto.mapper.SaleMapper;
import com.Progra1.Proyecto.persistence.Entity.ArticleSale;
import com.Progra1.Proyecto.persistence.Entity.Sale;
import com.Progra1.Proyecto.persistence.Repository.ArticleSaleRepository;
import com.Progra1.Proyecto.persistence.Repository.SaleRepository;
import com.Progra1.Proyecto.service.SaleService;
import com.Progra1.Proyecto.service.dto.SaleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleServiceImplement implements SaleService {

    private final SaleRepository saleRepository;
    private final ArticleSaleRepository articleSaleRepository;
    private final SaleMapper mapper;

    @Override
    public SaleDto save(SaleDto saleDto) {
        Optional<Sale> sale1 = saleRepository.findById(saleDto.getCode());

        if(sale1.isPresent()){
            throw new ResourceFoundException("Ya existe la venta con código: " + saleDto.getCode(),HttpStatus.CONFLICT);
        }

        Sale sale = mapper.saleDtoToSale(saleDto);

        return mapper.saleToSaleDto(saleRepository.save(sale));
    }

    @Override
    public SaleDto update(SaleDto saleDto) {
        Optional<Sale> sale1 = saleRepository.findById(saleDto.getCode());

        if(sale1.isEmpty()){
            throw new ResourceNotFoundException("No existe la venta con código: " + saleDto.getCode(),HttpStatus.NOT_FOUND);
        }

        Sale sale = mapper.saleDtoToSale(saleDto);

        return mapper.saleToSaleDto(saleRepository.save(sale));
    }

    @Override
    public Optional<SaleDto> findById(String code) {
        return saleRepository.findById(code).map(mapper::saleToSaleDto);
    }

    @Override
    public List<SaleDto> findAll() {
        return mapper.saleToSaleDto((List<Sale>)saleRepository.findAll());
    }

    @Override
    public List<SaleDto> findByClient(String code) {
        return mapper.saleToSaleDto(saleRepository.findAllByClientRif(code));
    }

    @Override
    public void delete(String code) {
        Optional<Sale> sale = saleRepository.findById(code);

        if(sale.isEmpty()){
            throw new ResourceNotFoundException("No existe la venta con código: " + code,HttpStatus.NOT_FOUND);
        }

        List<ArticleSale> articleSales = articleSaleRepository.findByArticleSalePK_SaleCode(code);

        if(!articleSales.isEmpty()){
            throw new ResourceIsBeingUsedException("La venta tiene artículos a su cargo",HttpStatus.CONFLICT);
        }

        saleRepository.delete(sale.get());

    }

}
