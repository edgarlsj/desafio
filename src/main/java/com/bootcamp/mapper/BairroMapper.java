package com.bootcamp.mapper;

import com.bootcamp.dto.BairroDTO;
import com.bootcamp.entities.Bairro;
import com.bootcamp.entities.Municipio;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BairroMapper {




    public BairroDTO toDto(Bairro bairro) {
        BairroDTO dto = new BairroDTO();
        dto.setCodigoBairro(bairro.getCodigoBairro());
        dto.setCodigoMunicipio(bairro.getMunicipio().getCodigoMunicipio());
        dto.setNome(bairro.getNome());
        dto.setStatus(bairro.getStatus());
        return dto;


    }

    public Bairro toEntity(BairroDTO dto) {
        Bairro bairro = new Bairro();

        bairro.setCodigoBairro(dto.getCodigoBairro());
        bairro.setNome(dto.getNome());
        bairro.setMunicipio(new Municipio(dto.getCodigoMunicipio(), null, null, 0));
        bairro.setStatus(dto.getStatus());
        return bairro;
    }


    }

