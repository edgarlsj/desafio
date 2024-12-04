package com.bootcamp.dto;

import com.bootcamp.entities.Bairro;
import com.bootcamp.entities.Endereco;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public class PessoaDTO {


    private Long codigoPessoa;
    private String nome;
    private String sobrenome;
    private Integer idade;
    private String login;
    private String senha;
    private Integer status;
    private List<EnderecoDTO> enderecos ;

}
