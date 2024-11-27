package com.bootcamp.controllers;

import com.bootcamp.dto.BairroDTO;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.exceptions.ErrorResponseDesafio;
import com.bootcamp.services.BairroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bairro")
public class BairroController {

    public final BairroService bairroService;

    @GetMapping
    public ResponseEntity<?> getAllBairros(
            @RequestParam(required = false) String codigoBairro,
            @RequestParam(required = false) Long codigoMunicipio,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status) {
        {
            try {
                Long codigoBairroLong = null;
                if (codigoBairro != null || codigoMunicipio != null) {
                    try {
                        codigoBairroLong = Long.parseLong(codigoBairro);

                    } catch (NumberFormatException e) {
                        return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possivel consultar bairro no banco de dados. Motivo: o Valor do campo codigoBairro precisa ser um numero . e você passou " + codigoBairro + "", 404));
                    }
                }
                if (codigoBairro != null) {
                    BairroDTO bairro = bairroService.getByCodigoBairro(codigoBairroLong);
                    return ResponseEntity.status(200).body(bairro);
                } else {
                    List<BairroDTO> bairros = bairroService.getAll(codigoMunicipio, nome, status);
                    return ResponseEntity.status(200).body(bairros);
                }

            } catch (Exception e) {
                return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível consultar bairro no banco de dados", 404));
            }
        }

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
