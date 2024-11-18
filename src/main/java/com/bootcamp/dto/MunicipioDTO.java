package com.bootcamp.dto;

import com.bootcamp.entities.UF;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MunicipioDTO {


    private Long codigoMunicipio;
    private Long codigoUF;
    private String nome;
    private int status;

}
