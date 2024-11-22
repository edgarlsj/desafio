package com.bootcamp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {


    private Long codigoBairro;
    private String nomeRua;
    private int numero;
    private String complemento;
    private String cep;



}
