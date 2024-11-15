package com.bootcamp.controllers;

import com.bootcamp.dto.BairroDTO;
import com.bootcamp.exceptions.DesafioExceptions;
import com.bootcamp.services.BairroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bairro")
public class BairroController {

    public final BairroService bairroService;

    @GetMapping
    public ResponseEntity<?> getAllBairros(
            @RequestParam(required = false) Long codigoBairro,
            @RequestParam(required = false) Long codigoMunicipio,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status) {
        {
            try {
                if (codigoBairro != null) {
                    BairroDTO bairro = bairroService.getByCodigoBairro(codigoBairro);
                    return ResponseEntity.status(200).body(bairro);
                } else {
                    List<BairroDTO> bairros = bairroService.getAll(codigoMunicipio, nome, status);
                    return ResponseEntity.status(200).body(bairros);
                }

            } catch (Exception e) {
                return ResponseEntity.status(404).body("Não foi possível consultar bairro no banco de dados");
            }
        }
    }


}
