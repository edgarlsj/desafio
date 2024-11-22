package com.bootcamp.mapper;

import com.bootcamp.dto.PessoaDTO;
import com.bootcamp.entities.Pessoa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PessoaMapper {

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
        dto.setEnderecos(isEndereco ? pessoa.getEndereco().stream().map(enderecoMapper::toEnderecoDTO).collect(Collectors.toList()): new ArrayList<>());
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
