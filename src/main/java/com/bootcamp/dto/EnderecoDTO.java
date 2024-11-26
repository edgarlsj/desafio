package com.bootcamp.dto;

import lombok.*;

@Getter
@Setter
public class EnderecoDTO {

    private Long codigoEndereco;
    private Long codigoPessoa;
    private Long codigoBairro;
    private String nomeRua;
    private int numero;
    private String complemento;
    private String cep;
    private BairroDTODetalhado bairro;


}
