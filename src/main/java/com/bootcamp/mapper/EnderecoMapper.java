package com.bootcamp.mapper;

import com.bootcamp.dto.BairroDTODetalhado;
import com.bootcamp.dto.EnderecoDTO;
import com.bootcamp.dto.MunicipioDTODetalhado;
import com.bootcamp.entities.Bairro;
import com.bootcamp.entities.Endereco;
import com.bootcamp.entities.Municipio;
import org.springframework.stereotype.Service;

@Service
public class EnderecoMapper {


    public EnderecoDTO toEnderecoDTO(Endereco endereco) {
        EnderecoDTO dto = new EnderecoDTO();

        dto.setCodigoEndereco(endereco.getCodigoEndereco());
        if (endereco.getPessoa() != null) {
            dto.setCodigoPessoa(endereco.getPessoa());
        }

        dto.setCodigoBairro(endereco.getBairro().getCodigoBairro());
        dto.setNomeRua(endereco.getNomeRua());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setCep(endereco.getCep());

        Bairro bairro = endereco.getBairro();
        Municipio municipio = bairro.getMunicipio();

        BairroDTODetalhado bairroDTO = new BairroDTODetalhado();
        bairroDTO.setCodigoBairro(bairro.getCodigoBairro());
        bairroDTO.setCodigoMunicipio(municipio.getCodigoMunicipio());
        bairroDTO.setNome(bairro.getNome());
        bairroDTO.setStatus(bairro.getStatus());

        MunicipioDTODetalhado municipioDTODetalhado = new MunicipioDTODetalhado();
        municipioDTODetalhado.setCodigoMunicipio(municipio.getCodigoMunicipio());
        municipioDTODetalhado.setCodigoUF(municipio.getUf().getCodigoUF());
        municipioDTODetalhado.setNome(municipio.getNome());
        municipioDTODetalhado.setStatus(municipio.getStatus());
        municipioDTODetalhado.setUf(new UFMapper().toDTO(municipio.getUf()));

        bairroDTO.setMunicipio(municipioDTODetalhado);
        dto.setBairro(bairroDTO);

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
