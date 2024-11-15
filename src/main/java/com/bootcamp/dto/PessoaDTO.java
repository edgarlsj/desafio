package com.bootcamp.dto;

import com.bootcamp.entities.Bairro;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {


    private Long codigoPessoa;
    private String nome;
    private String sobrenome;
    private int idade;
    private String login;
    private String senha;
    private int status;
    private Bairro codigoBairro;
}
