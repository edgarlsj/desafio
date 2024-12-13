package com.bootcamp.controllers;


import com.bootcamp.dto.MunicipioDTO;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.exceptions.ErrorResponseDesafio;
import com.bootcamp.services.MunicipioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/municipio")
@AllArgsConstructor
public class MunicipioController {


    public final MunicipioService municipioService;

    @GetMapping
    public ResponseEntity<?> getAllMunicipio(
            @RequestParam(required = false) String codigoMunicipio,
            @RequestParam(required = false) String codigoUF,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String status) {

        Long codigoMunicipioLong = null;
        Long codigoUfLong = null;
        Integer statusInt = null;

        // Validação para codigoMunicipio: assegura que apenas números são aceitos
        if (codigoMunicipio != null) {
            try {
                codigoMunicipioLong = Long.parseLong(codigoMunicipio);
            } catch (NumberFormatException e) {
                return ResponseEntity.status(404)
                        .body(new ErrorResponseDesafio(
                                "Não foi possível consultar Município no banco de dados. O campo codigoMunicipio deve conter apenas números.", 404));
            }
        }

        // Validação para codigoUF: assegura que apenas números são aceitos
        if (codigoUF != null) {
            try {
                codigoUfLong = Long.parseLong(codigoUF);
            } catch (NumberFormatException e) {
                return ResponseEntity.status(404)
                        .body(new ErrorResponseDesafio(
                                "Não foi possível consultar Município no banco de dados. O campo codigoUF deve conter apenas números.", 404));
            }
        }

        // Validação para status: assegura que apenas números são aceitos
        if (status != null) {
            try {
                statusInt = Integer.parseInt(status);
            } catch (NumberFormatException e) {
                return ResponseEntity.status(404)
                        .body(new ErrorResponseDesafio(
                                "Não foi possível consultar Município no banco de dados. O campo status deve conter apenas números.", 404));
            }
        }

        // Busca os municípios com os filtros validados
        List<MunicipioDTO> result = municipioService.findByFilters(
                Optional.ofNullable(codigoMunicipioLong),
                Optional.ofNullable(codigoUfLong),
                Optional.ofNullable(nome),
                Optional.ofNullable(statusInt));

        // Se apenas codigoMunicipio for fornecido e encontrar um único resultado, retorna o objeto
        if (codigoMunicipioLong != null && result.size() == 1) {
            return ResponseEntity.ok(result.get(0));
        }

        // Caso contrário, retorna a lista de municípios ou uma lista vazia
        return ResponseEntity.ok(result);
    }



    @PostMapping
    public ResponseEntity<?> createMunicipio (@RequestBody MunicipioDTO municipioDTO){
        try {
            List<MunicipioDTO> municipio = municipioService.createMunicipio(municipioDTO);
            return ResponseEntity.status(200).body(municipio);
        } catch (DesafioException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(new ErrorResponseDesafio(e.getMessage(), 404));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível criar municipio no banco de dados", 404));
        }
    }

    @PutMapping
    public ResponseEntity<?> updateMunicipio (@RequestBody MunicipioDTO municipioDTO){
        try {
            List<MunicipioDTO> municipio = municipioService.updateMunicipio(municipioDTO);
            return ResponseEntity.status(200).body(municipio);
        } catch (DesafioException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(new ErrorResponseDesafio(e.getMessage(), 404));
        }
    }
}

