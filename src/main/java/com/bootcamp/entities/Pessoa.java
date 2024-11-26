package com.bootcamp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PESSOA")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pessoa {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_seq")
    @SequenceGenerator(name = "pessoa_seq", sequenceName = "PESSOA_SEQ", allocationSize = 1)
    @Column(name = "CODIGO_PESSOA")
    private Long codigoPessoa;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SOBRENOME")
    private String sobrenome;

    @Column(name = "IDADE")
    private Integer idade;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "STATUS")
    private int status;


    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "ENDERECO")
    private List<Endereco> endereco;


}
