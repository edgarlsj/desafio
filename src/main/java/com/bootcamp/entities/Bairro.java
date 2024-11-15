package com.bootcamp.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "BAIRRO")
@Data

public class Bairro {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "CODIGO_BAIRRO")
        private Long codigoBairro;

        @Column(name = "NOME")
        private String nome;

        @ManyToOne
        @JoinColumn(name = "CODIGO_MUNICIPIO")
        private Municipio municipio;

        @Column(name = "STATUS")
        private Integer status;
}
