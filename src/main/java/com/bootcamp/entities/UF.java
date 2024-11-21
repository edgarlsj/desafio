package com.bootcamp.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "UF")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UF {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CODIGO_UF")
    private Long codigoUF;

    @Column(name = "SIGLA")
    private String sigla;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "STATUS")
    private int status;


}
