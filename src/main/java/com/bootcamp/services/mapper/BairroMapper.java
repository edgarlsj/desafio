package com.bootcamp.services.mapper;

import com.bootcamp.dto.BairroDTO;
import com.bootcamp.entities.Bairro;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BairroMapper {

    ModelMapper modelMapper;


    public BairroDTO toDto(Bairro bairro) {
        BairroDTO dto = new BairroDTO();
        dto.setCodigoBairro(bairro.getCodigoBairro());
        dto.setCodigoMunicipio(bairro.getMunicipio().getCodigoMunicipio());
        dto.setNome(bairro.getNome());
        dto.setStatus(bairro.getStatus());
        return dto;


//    public BairroDTO toDTO(Bairro bairro) {
//        BairroDTO bairroDTO = modelMapper.map(bairro, BairroDTO.class);
//        return bairroDTO;
//    }
//
//    public Bairro toEntity(BairroDTO dto) {
//        Bairro bairro = modelMapper.map(dto, Bairro.class);
//        return bairro;
//    }


//    public BairroMapper(MunicipioMapper municipioMapper) {
//        this.municipioMapper = municipioMapper;
//    }
//
//    MunicipioMapper municipioMapper;

//    public BairroDTO toDTO(Bairro bairro) {
//        BairroDTO dto = new BairroDTO();
//        dto.setCodigoBairro(bairro.getCodigoBairro());
//        dto.setNome(bairro.getNome());
//        dto.setMunicipio(municipioMapper.toDTO(bairro.getCodigoMunicipio()));
//        return dto;
//    }
//
//    public Bairro toEntity(BairroDTO dto) {
//        Bairro bairro = new Bairro();
//        bairro.setCodigoBairro(dto.getCodigoBairro());
//        bairro.setNome(dto.getNome());
//        bairro.setCodigoMunicipio(dto.getMunicipio());
//        bairro.setStatus(dto.getStatus());
//        return bairro;
//    }


    }
}
