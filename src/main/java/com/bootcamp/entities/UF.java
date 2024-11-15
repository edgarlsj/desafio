package com.bootcamp.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "UF")
@Data
public class UF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_UF")
    private Long codigoUF;

    @Column(name = "SIGLA")
    private String sigla;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "STATUS")
    private int status;


}
