package com.bootcamp.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode

public class MunicipioDTODetalhado {

    private Long codigoMunicipio;
    private Long codigoUF;
    private String nome;
    private int status;
    private UFDTO uf;
}
