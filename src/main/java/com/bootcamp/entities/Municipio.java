package com.bootcamp.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MUNICIPIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Municipio {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_MUNICIPIO")
    private Long codigoMunicipio;

    @Column(name = "NOME")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "CODIGO_UF")
    private UF uf;

    @Column(name = "STATUS")
    private int status;
}
