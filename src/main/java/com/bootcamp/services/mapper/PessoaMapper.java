package com.bootcamp.services.mapper;

import com.bootcamp.dto.PessoaDTO;
import com.bootcamp.entities.Pessoa;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PessoaMapper {


    public PessoaDTO toDTO(Pessoa pessoa, boolean isEndereco) {
        PessoaDTO dto = new PessoaDTO();
        dto.setCodigoPessoa(pessoa.getCodigoPessoa());
        dto.setNome(pessoa.getNome());
        dto.setSobrenome(pessoa.getSobrenome());
        dto.setIdade(pessoa.getIdade());
        dto.setLogin(pessoa.getLogin());
        dto.setSenha(pessoa.getSenha());
        dto.setStatus(pessoa.getStatus());
        dto.setEnderecos(pessoa.getEnderecos());
        return dto;
    }


}
