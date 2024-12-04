package com.bootcamp.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class BairroDTODetalhado {

    private Long codigoBairro;
    private Long codigoMunicipio;
    private String nome;
    private int status;
    private MunicipioDTODetalhado  municipio;
}
