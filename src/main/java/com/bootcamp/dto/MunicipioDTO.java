package com.bootcamp.dto;

import com.bootcamp.entities.UF;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioDTO {


    private Long codigoMunicipio;
    private Long codigoUF;
    private String nome;
    private Integer status;

}
