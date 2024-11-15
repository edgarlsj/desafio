package com.bootcamp.services.mapper;

import com.bootcamp.dto.MunicipioDTO;
import com.bootcamp.entities.Municipio;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MunicipioMapper {




    public MunicipioDTO toDTO(Municipio municipio) {
        MunicipioDTO dto = new MunicipioDTO();
        dto.setCodigoMunicipio(municipio.getCodigoMunicipio());
        dto.setCodigoUF(municipio.getUf().getCodigoUF());
        dto.setNome(municipio.getNome());
        dto.setStatus(municipio.getStatus());
        return dto;
    }

//    public Municipio toEntity(MunicipioDTO dto) {
//        Municipio municipio = new Municipio();
//        municipio.setCodigoMunicipio(dto.getCodigoMunicipio());
//        municipio.setNome(dto.getNome());
//        municipio.setUf(dto.getUf());
//        return municipio;
//    }
}
