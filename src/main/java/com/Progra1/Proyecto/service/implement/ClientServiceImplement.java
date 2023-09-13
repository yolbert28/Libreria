package com.Progra1.Proyecto.service.implement;

import com.Progra1.Proyecto.exception.ResourceFoundException;
import com.Progra1.Proyecto.exception.ResourceIsBeingUsedException;
import com.Progra1.Proyecto.exception.ResourceNotFoundException;
import com.Progra1.Proyecto.mapper.ClientMapper;
import com.Progra1.Proyecto.persistence.Entity.Client;
import com.Progra1.Proyecto.persistence.Entity.Sale;
import com.Progra1.Proyecto.persistence.Repository.ClientRepository;
import com.Progra1.Proyecto.persistence.Repository.SaleRepository;
import com.Progra1.Proyecto.service.ClientService;
import com.Progra1.Proyecto.service.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImplement implements ClientService {

    private final ClientRepository clientRepository;
    private final SaleRepository saleRepository;
    private final ClientMapper mapper;

    @Override
    public ClientDto save(ClientDto clientDto) {
        Optional<Client> client1 = clientRepository.findById(clientDto.getRif());

        if(client1.isPresent()){
            throw new ResourceFoundException("Ya existe el cliente con el Rif: " + clientDto.getRif(),HttpStatus.CONFLICT);
        }

        Client client = mapper.clientDtoToClient(clientDto);

        return mapper.clientToClientDto(clientRepository.save(client));
    }

    @Override
    public ClientDto update(ClientDto clientDto) {
        Optional<Client> client1 = clientRepository.findById(clientDto.getRif());

        if(client1.isEmpty()){
            throw new ResourceNotFoundException("No existe el cliente",HttpStatus.NOT_FOUND);
        }

        Client client = mapper.clientDtoToClient(clientDto);

        return mapper.clientToClientDto(clientRepository.save(client));
    }

    @Override
    public Optional<ClientDto> findByRif(String rif) {
        return clientRepository.findById(rif).map(mapper::clientToClientDto);
    }

    @Override
    public List<ClientDto> findAll() {
        return mapper.clientToClientDto((List<Client>)clientRepository.findAll());
    }

    @Override
    public List<ClientDto> findByStatus(String status) {
        return mapper.clientToClientDto(clientRepository.findAllByStatus(status));
    }

    @Override
    public void delete(String rif) {
        Optional<Client> client = clientRepository.findById(rif);

        if(client.isEmpty()) {
            throw new ResourceNotFoundException("No existe el cliente con el Rif: " + rif, HttpStatus.NOT_FOUND);
        }

        List<Sale> sales = saleRepository.findAllByClientRif(rif);

        if(!sales.isEmpty()){
            throw new ResourceIsBeingUsedException("El cliente tiene compras a su cargo",HttpStatus.CONFLICT);
        }

        clientRepository.delete(client.get());

    }
}
