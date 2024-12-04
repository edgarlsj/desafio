package com.bootcamp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_ENDERECO")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_seq")
    @SequenceGenerator(name = "endereco_seq", sequenceName = "ENDERECO_SEQ", allocationSize = 1)
    @Column(name = "CODIGO_ENDERECO")
    private Long codigoEndereco;


    @JoinColumn(name = "CODIGO_PESSOA")
    private Long pessoa;

    @ManyToOne
    @JoinColumn(name = "CODIGO_BAIRRO")
    private Bairro bairro;

    @Column(name = "NOME_RUA")
    private String nomeRua;

    @Column(name = "NUMERO")
    private int numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "CEP")
    private String cep;
}
