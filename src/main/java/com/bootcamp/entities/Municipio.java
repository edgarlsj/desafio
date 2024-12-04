package com.bootcamp.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_MUNICIPIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipio_seq")
    @SequenceGenerator(name = "municipio_seq", sequenceName = "MUNICIPIO_SEQ", allocationSize = 1)
    @Column(name = "CODIGO_MUNICIPIO", updatable = false, nullable = false)
    private Long codigoMunicipio;

    @Column(name = "NOME")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "CODIGO_UF")
    private UF uf;

    @Column(name = "STATUS")
    private int status;

}
