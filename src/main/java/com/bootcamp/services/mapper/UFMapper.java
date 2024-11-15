package com.bootcamp.services.mapper;

import com.bootcamp.dto.UFDTO;
import com.bootcamp.entities.UF;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UFMapper {



    public UFDTO toDTO(UF uf) {
        UFDTO ufDTO = new UFDTO();
        ufDTO.setCodigoUF(uf.getCodigoUF());
        ufDTO.setNome(uf.getNome());
        ufDTO.setSigla(uf.getSigla());
        ufDTO.setStatus(uf.getStatus());
        return ufDTO;
    }







//    ModelMapper modelMapper;
//
//    public UFDTO toDTO(UF uf) {
//        UFDTO ufDTO = modelMapper.map(uf, UFDTO.class);
//        return ufDTO;
//    }
//
//    public UF toEntity(UFDTO dto) {
//        UF uf = modelMapper.map(dto, UF.class);
//        return uf;
//    }
}
