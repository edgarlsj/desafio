package com.bootcamp.mapper;

import com.bootcamp.dto.MunicipioDTO;
import com.bootcamp.dto.UFDTO;
import com.bootcamp.entities.Municipio;
import com.bootcamp.entities.UF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MunicipioMapper {

    @Autowired
    UFMapper ufMapper;




    public MunicipioDTO toDTO(Municipio municipio) {
        MunicipioDTO dto = new MunicipioDTO();
        dto.setCodigoMunicipio(municipio.getCodigoMunicipio());
        dto.setCodigoUF(municipio.getUf().getCodigoUF());
        dto.setNome(municipio.getNome());
        dto.setStatus(municipio.getStatus());
        return dto;
    }

    public Municipio toEntity(MunicipioDTO dto) {
        Municipio municipio = new Municipio();

        if (dto.getCodigoMunicipio() != null) {
            municipio.setCodigoMunicipio(dto.getCodigoMunicipio());
        }
        municipio.setNome(dto.getNome());
        municipio.setStatus(dto.getStatus());
        municipio.setUf(ufMapper.toEntity(new UFDTO(dto.getCodigoUF(), null, null, 0)));
        return municipio;
    }
}
