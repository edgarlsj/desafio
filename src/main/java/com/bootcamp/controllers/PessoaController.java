package com.bootcamp.controllers;


import com.bootcamp.dto.PessoaDTO;
import com.bootcamp.dto.UFDTO;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.exceptions.ErrorResponseDesafio;
import com.bootcamp.services.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
public class PessoaController {


    private final PessoaService pessoaService;


    @GetMapping
    public ResponseEntity<?> getAllPessoa(@RequestParam(required = false) Long codigoPessoa,
                                          @RequestParam(required = false) String nome,
                                          @RequestParam(required = false) String sobrenome,
                                          @RequestParam(required = false) Integer idade,
                                          @RequestParam(required = false) String login,
                                          @RequestParam(required = false) String senha,
                                          @RequestParam(required = false) Integer status) {

        try {
            if (codigoPessoa != null) {
                PessoaDTO pessoaDTO = pessoaService.getFindById(codigoPessoa);
                return ResponseEntity.status(200).body(pessoaDTO);
            } else {
                List<PessoaDTO> pessoas = pessoaService.getAll(codigoPessoa, nome, sobrenome, idade, login, senha, status);
                return ResponseEntity.status(200).body(pessoas);
            }

        } catch (DesafioException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível consultar pessoa no banco de dados", 404));
        }

    }

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
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


}
