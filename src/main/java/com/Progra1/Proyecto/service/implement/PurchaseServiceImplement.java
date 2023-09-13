package com.Progra1.Proyecto.service.implement;

import com.Progra1.Proyecto.exception.ResourceFoundException;
import com.Progra1.Proyecto.exception.ResourceIsBeingUsedException;
import com.Progra1.Proyecto.exception.ResourceNotFoundException;
import com.Progra1.Proyecto.mapper.PurchaseMapper;
import com.Progra1.Proyecto.persistence.Entity.ArticlePurchase;
import com.Progra1.Proyecto.persistence.Entity.Purchase;
import com.Progra1.Proyecto.persistence.Repository.ArticlePurchaseRepository;
import com.Progra1.Proyecto.persistence.Repository.PurchaseRepository;
import com.Progra1.Proyecto.service.PurchaseService;
import com.Progra1.Proyecto.service.dto.PurchaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImplement implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ArticlePurchaseRepository articlePurchaseRepository;
    private final PurchaseMapper mapper;

    @Override
    public PurchaseDto save(PurchaseDto purchaseDto) {

        Optional<Purchase> purchaseCheck = purchaseRepository.findById(purchaseDto.getCode());

        if(purchaseCheck.isPresent()){
            throw new ResourceFoundException("Ya existe una compra con el código: " + purchaseDto.getCode(), HttpStatus.CONFLICT);
        }

        Purchase purchase = mapper.purchaseDtoToPurchase(purchaseDto);

        return mapper.purchaseToPurchaseDto(purchaseRepository.save(purchase));

    }

    @Override
    public PurchaseDto update(PurchaseDto purchaseDto) {

        Optional<Purchase> purchaseCheck = purchaseRepository.findById(purchaseDto.getCode());

        if(purchaseCheck.isEmpty()){
            throw new ResourceNotFoundException("No existe una compra con el código: " + purchaseDto.getCode(), HttpStatus.NOT_FOUND);
        }

        Purchase purchase = mapper.purchaseDtoToPurchase(purchaseDto);

        return mapper.purchaseToPurchaseDto(purchaseRepository.save(purchase));

    }

    @Override
    public Optional<PurchaseDto> findById(String code) {
        return purchaseRepository.findById(code).map(mapper::purchaseToPurchaseDto);
    }

    @Override
    public List<PurchaseDto> findAll() {
        return mapper.purchaseToPurchaseDto ((List<Purchase>)purchaseRepository.findAll());
    }

    @Override
    public List<PurchaseDto> findByProvider(String rif) {
        return mapper.purchaseToPurchaseDto(purchaseRepository.findAllByProviderRif(rif));
    }

    @Override
    public void delete(String code) {
        Optional<Purchase> purchaseCheck = purchaseRepository.findById(code);

        if(purchaseCheck.isEmpty()){
            throw new ResourceNotFoundException("No existe una compra con el código: " + code,HttpStatus.NOT_FOUND);
        }

        List<ArticlePurchase> articlePurchasesCheck = articlePurchaseRepository.findByArticlePurchasePK_PurchaseCode(code);

        if(!articlePurchasesCheck.isEmpty()){
            throw new ResourceIsBeingUsedException("La compra tiene artículos asignados",HttpStatus.CONFLICT);
        }

        purchaseRepository.delete(purchaseCheck.get());
    }

}
