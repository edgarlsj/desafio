package com.bootcamp.controllers;

import com.bootcamp.dto.BairroDTO;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.exceptions.ErrorResponseDesafio;
import com.bootcamp.services.BairroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/bairro")
public class BairroController {

    public final BairroService bairroService;

    @GetMapping

    public ResponseEntity<?> getBairro(
            @RequestParam(required = false) String codigoBairro,
            @RequestParam(required = false) String codigoMunicipio,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String status) {

        Long codigoBairroLong = null;
        Long codigoMunicipioLong = null;
        Integer statusInt = null;

        // Validação para codigoBairro: garante que apenas números são aceitos
        if (codigoBairro != null) {
            try {
                codigoBairroLong = Long.parseLong(codigoBairro);
            } catch (NumberFormatException e) {
                return ResponseEntity.status(404)
                        .body(new ErrorResponseDesafio(
                                "Não foi possível consultar Bairro no banco de dados. O campo codigoBairro deve conter apenas números.", 404));
            }
        }

        // Validação para codigoMunicipio: garante que apenas números são aceitos
        if (codigoMunicipio != null) {
            try {
                codigoMunicipioLong = Long.parseLong(codigoMunicipio);
            } catch (NumberFormatException e) {
                return ResponseEntity.status(404)
                        .body(new ErrorResponseDesafio(
                                "Não foi possível consultar Bairro no banco de dados. O campo codigoMunicipio deve conter apenas números.", 404));
            }
        }

        // Validação para status: garante que apenas números são aceitos
        if (status != null) {
            try {
                statusInt = Integer.parseInt(status);
            } catch (NumberFormatException e) {
                return ResponseEntity.status(404)
                        .body(new ErrorResponseDesafio(
                                "Não foi possível consultar Bairro no banco de dados. O campo status deve conter apenas números.", 404));
            }
        }

        // Busca os bairros com os filtros validados
        List<BairroDTO> result = bairroService.findFilters(
                Optional.ofNullable(codigoBairroLong),
                Optional.ofNullable(codigoMunicipioLong),
                Optional.ofNullable(nome),
                Optional.ofNullable(statusInt));

        // retorna o objeto se apenas codigoBairro for fornecido e encontrar um único resultado
        if (codigoBairroLong != null && result.size() == 1) {
            return ResponseEntity.ok(result.get(0));
        }

        // Caso contrário, retorna a lista de bairros ou uma lista vazia
        return ResponseEntity.ok(result);
    }






    @PostMapping
    public ResponseEntity<?> createBairro(@RequestBody BairroDTO bairroDTO) {
        try {
            List<BairroDTO> bairro = bairroService.createBairro(bairroDTO);
            return ResponseEntity.status(200).body(bairro);
        } catch (DesafioException e) {
            return ResponseEntity.status(404).body(new ErrorResponseDesafio(e.getMessage(), 404));
        }catch (Exception e) {
            return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível criar bairro no banco de dados", 404));
        }

    }

    @PutMapping
    public ResponseEntity<?> updateBairro(@RequestBody BairroDTO bairroDTO) {
        try {
            List<BairroDTO> bairro = bairroService.updateBairro(bairroDTO);
            return ResponseEntity.status(200).body(bairro);
        } catch (DesafioException e) {
            return ResponseEntity.status(404).body(new ErrorResponseDesafio(e.getMessage(), 404));
        }
    }


}
