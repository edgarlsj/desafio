package com.bootcamp.mapper;

import com.bootcamp.dto.UFDTO;
import com.bootcamp.entities.UF;
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

    public UF toEntity(UFDTO dto) {
        UF uf = new UF();

        if (dto.getCodigoUF() != null) {
            uf.setCodigoUF(dto.getCodigoUF());
        }
        uf.setNome(dto.getNome());
        uf.setSigla(dto.getSigla());
        uf.setStatus(dto.getStatus());
        return uf;
    }





}
