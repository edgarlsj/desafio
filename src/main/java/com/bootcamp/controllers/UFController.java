package com.bootcamp.controllers;

import com.bootcamp.dto.UFDTO;
import com.bootcamp.services.UFService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/uf")

public class UFController {


    private final UFService ufService;


    @GetMapping
    public ResponseEntity<?> getAllUF(@RequestParam (required = false) Long codigoUF,
                                      @RequestParam (required = false) String nome,
                                      @RequestParam (required = false) String sigla,
                                      @RequestParam (required = false) Integer status){

        try{
            if (codigoUF != null){
                UFDTO uf = ufService.getFindById(codigoUF);
                return ResponseEntity.status(200).body(uf);
            }else {
                List<UFDTO> ufs = ufService.getAll(codigoUF, nome, sigla, status);
                return ResponseEntity.status(200).body(ufs);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).body("Não foi possível consultar UF no banco de dados");
        }

    }

    @PostMapping
    public ResponseEntity<?> createUF(@Valid @RequestBody UFDTO ufDTO){
        try{
           List<UFDTO>  uf = ufService.create(ufDTO);
            return ResponseEntity.status(200).body(uf);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).body("Não foi possível criar UF no banco de dados");
        }
    }

}
