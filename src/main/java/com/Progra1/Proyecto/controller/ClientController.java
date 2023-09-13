package com.Progra1.Proyecto.controller;

import com.Progra1.Proyecto.service.ClientService;
import com.Progra1.Proyecto.service.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;


    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable(value = "id") String rif){
        return clientService.findByRif(rif).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<ClientDto> saveClient(@RequestBody ClientDto clientDto){
        return new ResponseEntity<>(clientService.save(clientDto),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto){
        return new ResponseEntity<>(clientService.update(clientDto),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientDto>> getAll(){
        return new ResponseEntity<>(clientService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getStatus/{status}")
    public ResponseEntity<List<ClientDto>> getByStatus(@PathVariable(value = "status") String status){
        return new ResponseEntity<>(clientService.findByStatus(status),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{rif}")
    public ResponseEntity<Void> deleteClient(@PathVariable String rif){

        clientService.delete(rif);

        return ResponseEntity.ok().build();

    }

}
