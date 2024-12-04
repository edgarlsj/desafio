package com.bootcamp.dto;

import com.bootcamp.entities.Municipio;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BairroDTO {


    private Long codigoBairro;
    private Long codigoMunicipio;
    private String nome;
    private int status;
}
