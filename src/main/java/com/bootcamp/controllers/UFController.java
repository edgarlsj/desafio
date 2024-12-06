package com.bootcamp.controllers;

import com.bootcamp.dto.UFDTO;
import com.bootcamp.exceptions.ErrorResponseDesafio;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.services.UFService;
import com.bootcamp.util.ConverterUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/uf")

public class UFController {


    private final UFService ufService;


//    @GetMapping
//    public ResponseEntity<?> getAllUF(@RequestParam(required = false) String codigoUF,
//                                      @RequestParam(required = false) String nome,
//                                      @RequestParam(required = false) String sigla,
//                                      @RequestParam(required = false) String status) {
//
//        try {
//            Long codigoUFLong = null;
//            if (codigoUF != null) {
//                try {
//                    codigoUFLong = Long.parseLong(codigoUF);
//                } catch (NumberFormatException e) {
//                    return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possivel consultar UF no banco de dados. Motivo: o Valor do campo codigoUF precisa ser um numero . e você passou "+codigoUF+"", 404));
//                }
//            }
//            //Se o código da UF for informado, busca a UF pelo código
//            if (codigoUF != null) {
//                Object uf = ufService.getFindById(codigoUFLong);
//                return ResponseEntity.status(200).body(uf);
//
//
//            } else if (sigla != null && nome != null) {
//                Object ufBySigla = ufService.getFindBySigla(sigla);
//                Object ufByNome = ufService.getFindByNome(nome);
//                if (ufBySigla.equals(ufByNome)) {
//                    return ResponseEntity.status(200).body(ufBySigla);
//                } else{
//                    return ResponseEntity.status(200).body(new ArrayList<>());
//                }
//
//
//            } else if (sigla != null) {
//                Object uf = ufService.getFindBySigla(sigla);
//                return ResponseEntity.status(200).body(uf);
//
//            } else if (nome != null) {
//                Object uf = ufService.getFindByNome(nome);
//                return ResponseEntity.status(200).body(uf);
//            } else {
//                ;
//
//                List<UFDTO> ufs = ufService.getAll(codigoUFLong, nome, sigla, ConverterUtil.convertStringToInteger(status));
//                return ResponseEntity.status(200).body(ufs);
//            }
//
//        } catch (DesafioException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(404).body(new ErrorResponseDesafio(e.getMessage(), 404));
//        }
//
//    }

    @GetMapping
    public ResponseEntity<Object> getAllUF(
            @RequestParam(required = false) Long codigoUF,
            @RequestParam(required = false) String sigla,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status
    ) {

        try {
            if (status != null && codigoUF == null && sigla == null && nome == null) {
                return ResponseEntity.status(HttpStatus.OK).body(ufService.findAll(status));
            }
            if (codigoUF == null && sigla == null && nome == null) {
                return ResponseEntity.status(HttpStatus.OK).body(ufService.findAll());
            }
            return ResponseEntity.status(HttpStatus.OK).body(ufService.findOne(codigoUF, sigla != null ? sigla.toUpperCase() : null, nome != null ? nome.toUpperCase() : null, status));
        } catch (DesafioException e) {
            return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível consultar UF no banco de dados", 404));
        }
    }



    @PostMapping
    public ResponseEntity<?> createUF(@Valid @RequestBody UFDTO ufDTO) {
        try {
            List<UFDTO> uf = ufService.create(ufDTO);
            return ResponseEntity.status(200).body(uf);
        } catch (DesafioException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(new ErrorResponseDesafio(e.getMessage(), 404));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível criar UF no banco de dados", 404));
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUF(@Valid @RequestBody UFDTO ufDTO) {
        try {
            List<UFDTO> uf = ufService.update(ufDTO);
            return ResponseEntity.status(200).body(uf);
        } catch (DesafioException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(new ErrorResponseDesafio(e.getMessage(), 404));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível atualizar UF no banco de dados", 404));
        }
    }

}

