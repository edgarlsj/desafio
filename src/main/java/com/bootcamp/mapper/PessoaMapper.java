package com.bootcamp.mapper;

import com.bootcamp.dto.BairroDTO;
import com.bootcamp.dto.PessoaDTO;
import com.bootcamp.entities.Pessoa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class PessoaMapper {


    @Autowired
    EnderecoMapper enderecoMapper;


    public PessoaDTO toDTO(Pessoa pessoa, boolean isEndereco) {
        PessoaDTO dto = new PessoaDTO();
        dto.setCodigoPessoa(pessoa.getCodigoPessoa());
        dto.setNome(pessoa.getNome());
        dto.setSobrenome(pessoa.getSobrenome());
        dto.setIdade(pessoa.getIdade());
        dto.setLogin(pessoa.getLogin());
        dto.setSenha(pessoa.getSenha());
        dto.setStatus(pessoa.getStatus());
        dto.setEnderecos(isEndereco ? dto.getEnderecos(): new ArrayList<>()); //se for true traz os endereços se não traz uma lista vazia de endereços conforme a regra de negócio
        return dto;
    }

    public Pessoa toEntity(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigoPessoa(pessoaDTO.getCodigoPessoa());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setSobrenome(pessoaDTO.getSobrenome());
        pessoa.setIdade(pessoaDTO.getIdade());
        pessoa.setLogin(pessoaDTO.getLogin());
        pessoa.setSenha(pessoaDTO.getSenha());
        pessoa.setStatus(pessoaDTO.getStatus());
        pessoa.setEndereco(pessoaDTO.getEnderecos().stream().map(enderecoMapper::toEnderecoEntity).collect(Collectors.toList()));
        return pessoa;


    }


}
