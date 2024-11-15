package com.bootcamp.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UFDTO {

    private Long codigoUF;
    private String sigla;
    private String nome;
    private int status;
}
