package com.bootcamp.services.mapper;

import com.bootcamp.dto.PessoaDTO;
import com.bootcamp.entities.Pessoa;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PessoaMapper {

    ModelMapper modelMapper;

    public PessoaDTO toDTO(Pessoa pessoa) {
        PessoaDTO pessoaDTO = modelMapper.map(pessoa, PessoaDTO.class);
        return pessoaDTO;
    }

    public Pessoa toEntity(PessoaDTO dto) {
        Pessoa pessoa = modelMapper.map(dto, Pessoa.class);
        return pessoa;
    }
}
