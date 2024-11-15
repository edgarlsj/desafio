package com.bootcamp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BairroDTO {


    private Long codigoBairro;
    private Long codigoMunicipio;
    private String nome;
    private Integer status;
}
