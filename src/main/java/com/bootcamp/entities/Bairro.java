package com.bootcamp.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BAIRRO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bairro {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bairro_seq")
        @SequenceGenerator(name = "bairro_seq", sequenceName = "BAIRRO_SEQ", allocationSize = 1)
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
