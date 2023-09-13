package com.Progra1.Proyecto.controller;

import com.Progra1.Proyecto.service.ProviderService;
import com.Progra1.Proyecto.service.dto.ProviderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/provider")
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDto> getProvider(@PathVariable(value = "id") String rif){
        return providerService.findByRif(rif).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<ProviderDto> saveProvider(@RequestBody ProviderDto providerDto){
        return new ResponseEntity<>(providerService.save(providerDto),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ProviderDto> updateProvider(@RequestBody ProviderDto providerDto){
        return new ResponseEntity<>(providerService.update(providerDto),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProviderDto>> getAll(){
        return new ResponseEntity<>(providerService.findAll(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{rif}")
    public ResponseEntity<Void> deleteProvider(@PathVariable(value = "rif") String rif){
        providerService.delete(rif);
        return ResponseEntity.ok().build();
    }

}
