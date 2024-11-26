package com.bootcamp.services;

import com.bootcamp.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;


    public List getAllEnderecos(Long idBairro, Long idMunicipio, Long idUF, String cep, String logradouro, Integer numero, String complemento, Integer status) {
        return enderecoRepository.findAll();
    }




}
