package com.bootcamp.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UFDTO {




    private Long codigoUF;
    private String sigla;
    private String nome;
    private int status;




}
