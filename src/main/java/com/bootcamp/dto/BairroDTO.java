package com.bootcamp.dto;

import com.bootcamp.entities.Municipio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BairroDTO {


    private Long codigoBairro;
    private Long codigoMunicipio;
    private String nome;
    private int status;
}
