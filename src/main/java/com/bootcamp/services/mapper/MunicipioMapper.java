package com.bootcamp.services.mapper;

import com.bootcamp.dto.MunicipioDTO;
import com.bootcamp.entities.Municipio;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MunicipioMapper {

    ModelMapper modelMapper;



    public MunicipioDTO toDTO(Municipio municipio) {
        MunicipioDTO municipioDTO = modelMapper.map(municipio, MunicipioDTO.class);
        return municipioDTO;
    }

    public Municipio toEntity(MunicipioDTO dto) {
        Municipio municipio = modelMapper.map(dto, Municipio.class);
        return municipio;
    }






//    public MunicipioMapper(UFMapper ufMapper) {
//        this.ufMapper = ufMapper;
//    }
//    UFMapper ufMapper;
//
//    public MunicipioDTO toDTO(Municipio municipio) {
//        MunicipioDTO dto = new MunicipioDTO();
//        dto.setCodigoMunicipio(municipio.getCodigoMunicipio());
//        dto.setNome(municipio.getNome());
//        dto.setUf(municipio.getUf());
//        return dto;
//    }
//
//    public Municipio toEntity(MunicipioDTO dto) {
//        Municipio municipio = new Municipio();
//        municipio.setCodigoMunicipio(dto.getCodigoMunicipio());
//        municipio.setNome(dto.getNome());
//        municipio.setUf(dto.getUf());
//        return municipio;
//    }
}
