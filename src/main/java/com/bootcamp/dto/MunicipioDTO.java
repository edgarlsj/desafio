package com.bootcamp.dto;

import com.bootcamp.entities.UF;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MunicipioDTO {


    private Long codigoMunicipio;
    private String nome;
    private UFDTO ufdto;
    private int status;
}
