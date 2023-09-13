package com.Progra1.Proyecto.service.implement;

import com.Progra1.Proyecto.exception.ResourceFoundException;
import com.Progra1.Proyecto.exception.ResourceIsBeingUsedException;
import com.Progra1.Proyecto.exception.ResourceNotFoundException;
import com.Progra1.Proyecto.mapper.ProviderMapper;
import com.Progra1.Proyecto.persistence.Entity.Provider;
import com.Progra1.Proyecto.persistence.Entity.Purchase;
import com.Progra1.Proyecto.persistence.Repository.ProviderRepository;
import com.Progra1.Proyecto.persistence.Repository.PurchaseRepository;
import com.Progra1.Proyecto.service.ProviderService;
import com.Progra1.Proyecto.service.dto.ProviderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderServiceImplement implements ProviderService {

    private final ProviderRepository providerRepository;
    private final PurchaseRepository purchaseRepository;
    private final ProviderMapper mapper;


    @Override
    public ProviderDto save(ProviderDto providerDto) {
        Optional<Provider> providerCheck = providerRepository.findById(providerDto.getRif());

        if(providerCheck.isPresent()){
            throw new ResourceFoundException("El proveedor con código: " + providerDto.getRif() + ". Ya existe", HttpStatus.CONFLICT);
        }

        Provider provider = mapper.providerDtoToProvider(providerDto);

        return mapper.providerToProviderDto(providerRepository.save(provider));
    }

    @Override
    public ProviderDto update(ProviderDto providerDto) {
        Optional<Provider> providerCheck = providerRepository.findById(providerDto.getRif());

        if(providerCheck.isEmpty()){
            throw new ResourceNotFoundException("El proveedor con código: " + providerDto.getRif() + ". No existe", HttpStatus.NOT_FOUND);
        }

        Provider provider = mapper.providerDtoToProvider(providerDto);

        return mapper.providerToProviderDto(providerRepository.save(provider));
    }

    @Override
    public Optional<ProviderDto> findByRif(String rif) {
        return providerRepository.findById(rif).map(mapper::providerToProviderDto);
    }

    @Override
    public List<ProviderDto> findAll() {
        return mapper.providerToProviderDto((List<Provider>) providerRepository.findAll());
    }

    @Override
    public void delete(String rif) {

        Optional<Provider> providerCheck = providerRepository.findById(rif);

        if(providerCheck.isEmpty()) {
            throw new ResourceNotFoundException("El proveedor con rif: " + rif + ". No existe", HttpStatus.NOT_FOUND);
        }

        List<Purchase> purchasesCheck = purchaseRepository.findAllByProviderRif(rif);

        if(!purchasesCheck.isEmpty()){
            throw new ResourceIsBeingUsedException("El proveedor tiene compras asignadas",HttpStatus.CONFLICT);
        }

        providerRepository.delete(providerCheck.get());

    }

}