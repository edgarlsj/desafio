package com.bootcamp.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UFDTO {




    private Long codigoUF;
    private String sigla;
    private String nome;
    private int status;




}
