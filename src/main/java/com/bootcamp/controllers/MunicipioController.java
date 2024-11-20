package com.bootcamp.controllers;


import com.bootcamp.dto.BairroDTO;
import com.bootcamp.dto.MunicipioDTO;
import com.bootcamp.services.MunicipioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipio")
@AllArgsConstructor
public class MunicipioController {


    public final MunicipioService municipioService;



    @GetMapping
    public ResponseEntity<?> getAllMunicipios(@RequestParam (required = false) Long codigoMunicipio,
                                              @RequestParam (required = false) Long codigoUF,
                                              @RequestParam (required = false) String nome,
                                              @RequestParam (required = false) Integer status) {

        try {
            if (codigoMunicipio != null) {
                MunicipioDTO municipio = municipioService.getByCodigoMunicipio(codigoMunicipio);
                return ResponseEntity.status(200).body(municipio);
            } else {
                List<MunicipioDTO> municipios = municipioService.getAll(codigoMunicipio, codigoUF, nome, status);
                return ResponseEntity.status(200).body(municipios);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Não foi possível consultar municipio no banco de dados");
        }
    }

    @PostMapping
    public ResponseEntity<?> createMunicipio (@RequestBody MunicipioDTO municipioDTO){
        try {
            List<MunicipioDTO> municipio = municipioService.createMunicipio(municipioDTO);
            return ResponseEntity.status(200).body(municipio);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Não foi possível criar municipio");
        }
    }
}

