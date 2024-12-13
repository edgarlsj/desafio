package com.bootcamp.controllers;


import com.bootcamp.dto.PessoaDTO;
import com.bootcamp.dto.UFDTO;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.exceptions.ErrorResponseDesafio;
import com.bootcamp.services.PessoaService;
import com.bootcamp.util.ConverterUtil;
import com.bootcamp.util.ValidateUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
public class PessoaController {


    private final PessoaService pessoaService;



    @GetMapping
    public ResponseEntity<?> getPessoas(
            @RequestParam(required = false) String codigoPessoa,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String status) {


        try {


            Long codigoPessoaLong = null;
            Integer statusInt = null;

            // Validação para codigoPessoa: garante que apenas números são aceitos
            if (codigoPessoa != null) {
                try {
                    codigoPessoaLong = Long.parseLong(codigoPessoa);
                } catch (NumberFormatException e) {
                    return ResponseEntity.status(404)
                            .body(new ErrorResponseDesafio(
                                    "Não foi possível consultar Pessoa no banco de dados. O campo codigoPessoa deve conter apenas números.", 404));
                }
            }

            // Validação para status: garante que apenas números são aceitos
            if (status != null) {
                try {
                    statusInt = Integer.parseInt(status);
                } catch (NumberFormatException e) {
                    return ResponseEntity.status(404)
                            .body(new ErrorResponseDesafio(
                                    "Não foi possível consultar Pessoa no banco de dados. O campo status deve conter apenas números.", 404));
                }
            }

            List<PessoaDTO> pessoas = pessoaService.findByFilters(
                    Optional.ofNullable(codigoPessoaLong),
                    Optional.ofNullable(login),
                    Optional.ofNullable(statusInt));


            if (codigoPessoaLong != null && login == null && statusInt == null) {
                PessoaDTO pessoa = pessoaService.getFindById(codigoPessoaLong);
                List<PessoaDTO> pessoaDTOS = new ArrayList<>();
                if (pessoa != null) {
                    pessoaDTOS.add(pessoa);
                    return ResponseEntity.ok(pessoaDTOS.get(0));
                } else {
                    return ResponseEntity.status(200).body(pessoaDTOS);
                }
            }


            if (codigoPessoaLong != null && pessoas.size() == 1) {
                return ResponseEntity.ok(pessoas.get(0));
            }

            return ResponseEntity.ok(pessoas);

        } catch (DesafioException e) {
            return ResponseEntity.status(404).body(new ErrorResponseDesafio(e.getMessage(), 404));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível consultar Pessoa no banco de dados", 404));

        }
    }


//    @GetMapping
//    public ResponseEntity<?> getAllPessoa(@RequestParam(required = false) String codigoPessoa,
//                                          @RequestParam(required = false) String nome,
//                                          @RequestParam(required = false) String sobrenome,
//                                          @RequestParam(required = false) Integer idade,
//                                          @RequestParam(required = false) String login,
//                                          @RequestParam(required = false) String senha,
//                                          @RequestParam(required = false) String status) {
//
//        try {
//            Long codigoPessoaLong = null;
//            if (codigoPessoa != null) {
//                try {
//                    codigoPessoaLong = Long.parseLong(codigoPessoa);
//                } catch (NumberFormatException e) {
//                    return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possivel consultar pessoa no banco de dados. Motivo: o Valor do campo codigoPessoa precisa ser um numero . e você passou "+codigoPessoa+"", 404));
//                }
//            }
//            if (codigoPessoa != null) {
//                Object pessoaDTO = pessoaService.getFindById(codigoPessoaLong);
//                return ResponseEntity.status(200).body(pessoaDTO);
//            } else {
//                Integer statusInt = null;
//                if (status != null) {
//                    try {
//                        statusInt = Integer.parseInt(status);
//                    } catch (NumberFormatException e) {
//                        return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possivel consultar pessoa no banco de dados. Motivo: o Valor do campo status precisa ser um numero . e você passou "+status+"", 404));
//                    }
//                }
//                List<PessoaDTO> pessoas = pessoaService.getAll(codigoPessoaLong, nome, sobrenome, idade, login, senha, statusInt);
//                return ResponseEntity.status(200).body(pessoas);
//            }
//
//        } catch (DesafioException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível criar pessoa no banco de dados", 404));
//        }
//
//    }

    @PostMapping
    public ResponseEntity<?> createPessoa(@RequestBody PessoaDTO pessoaDTO) {
        try {
            List<PessoaDTO> pessoa = pessoaService.createPessoa(pessoaDTO);
            return ResponseEntity.status(200).body(pessoa);
        } catch (DesafioException e) {
            return ResponseEntity.status(404).body(new ErrorResponseDesafio(e.getMessage(), 404));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível criar pessoa no banco de dados", 404));

        }
    }

    @PutMapping
    public ResponseEntity<?> updatePessoa(@RequestBody PessoaDTO pessoaDTO) {
        try {
            List<PessoaDTO> pessoa = pessoaService.updatePessoa(pessoaDTO);
            return ResponseEntity.status(200).body(pessoa);
        } catch (DesafioException e) {
            return ResponseEntity.status(404).body(new ErrorResponseDesafio(e.getMessage(), 404));
        }catch (Exception e) {
            return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível atualizar pessoa no banco de dados", 404));

        }
    }


}
