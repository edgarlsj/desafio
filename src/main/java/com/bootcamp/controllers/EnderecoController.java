package com.bootcamp.controllers;

import com.bootcamp.dto.EnderecoDTO;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.exceptions.ErrorResponseDesafio;
import com.bootcamp.services.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/endereco")
@AllArgsConstructor

public class EnderecoController {


    private final EnderecoService enderecoService;

    public ResponseEntity<?> getAllEnderecos(@RequestParam(required = false) Long idBairro,
                                             @RequestParam(required = false) Long idMunicipio,
                                             @RequestParam(required = false) Long idUF,
                                             @RequestParam(required = false) String cep,
                                             @RequestParam(required = false) String logradouro,
                                             @RequestParam(required = false) Integer numero,
                                             @RequestParam(required = false) String complemento,
                                             @RequestParam(required = false) Integer status) {
        try {
            List<EnderecoDTO> enderecos = enderecoService.getAllEnderecos(idBairro, idMunicipio, idUF, cep, logradouro, numero, complemento, status);
            return ResponseEntity.status(200).body(enderecos);
        } catch (DesafioException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível consultar endereços no banco de dados", 404));
        }
    }
//
//    public ResponseEntity<?> createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
//        try {
//            List<EnderecoDTO> endereco = enderecoService.createEndereco(enderecoDTO);
//            return ResponseEntity.status(200).body(endereco);
//        } catch (DesafioException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(404).body(new ErrorResponseDesafio(e.getMessage(), 404));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível criar endereço no banco de dados", 404));
//        }
//    }
//
//    public ResponseEntity<?> updateEndereco(@RequestBody EnderecoDTO enderecoDTO) {
//        try {
//            List<EnderecoDTO> endereco = enderecoService.updateEndereco(enderecoDTO);
//            return ResponseEntity.status(200).body(endereco);
//        } catch (DesafioException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(404).body(new ErrorResponseDesafio(e.getMessage(), 404));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(404).body(new ErrorResponseDesafio("Não foi possível atualizar endereço no banco de dados", 404));
//        }
//    }
//
//    public ResponseEntity<?> deleteEndereco(@RequestParam Long idEndereco) {
//        try {
//            List<EnderecoDTO> endereco = enderecoService.deleteEndereco(idEndereco);
//            return ResponseEntity.status(200
//

}
