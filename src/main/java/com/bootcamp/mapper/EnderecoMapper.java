package com.bootcamp.mapper;

import com.bootcamp.dto.EnderecoDTO;
import com.bootcamp.entities.Bairro;
import com.bootcamp.entities.Endereco;
import org.springframework.stereotype.Service;

@Service
public class EnderecoMapper {


    public EnderecoDTO toEnderecoDTO(Endereco endereco) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setCodigoBairro(endereco.getBairro().getCodigoBairro());
        dto.setNomeRua(endereco.getNomeRua());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setCep(endereco.getCep());
        return dto;
    }

     Endereco toEnderecoEntity(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setNomeRua(enderecoDTO.getNomeRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setBairro(new Bairro(enderecoDTO.getCodigoBairro(), null, null, 0));
        return endereco;
    }
}
