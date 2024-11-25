package com.bootcamp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MunicipioDTODetalhado {

    private Long codigoMunicipio;
    private Long codigoUF;
    private String nome;
    private int status;
    private UFDTO uf;
}
