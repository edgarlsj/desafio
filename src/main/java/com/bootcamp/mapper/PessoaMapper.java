package com.bootcamp.mapper;

import com.bootcamp.dto.BairroDTO;
import com.bootcamp.dto.PessoaDTO;
import com.bootcamp.entities.Pessoa;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class PessoaMapper {

    private BairroMapper bairroMapper;


    public PessoaDTO toDTO(Pessoa pessoa, boolean isEndereco) {
        PessoaDTO dto = new PessoaDTO();
        dto.setCodigoPessoa(pessoa.getCodigoPessoa());
        dto.setNome(pessoa.getNome());
        dto.setSobrenome(pessoa.getSobrenome());
        dto.setIdade(pessoa.getIdade());
        dto.setLogin(pessoa.getLogin());
        dto.setSenha(pessoa.getSenha());
        dto.setStatus(pessoa.getStatus());
        dto.setEnderecos(isEndereco ? pessoa.getEnderecos(): new ArrayList<>()); //se for true traz os endereços se não traz uma lista vazia de endereços conforme a regra de negócio
        return dto;
    }


}
