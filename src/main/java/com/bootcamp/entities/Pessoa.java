package com.bootcamp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PESSOA")
@Data

public class Pessoa {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
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
            private Integer status;

            @ManyToMany//anotação que indica que a relação é de muitos para muitos
            @JoinTable(name = "PESSOA_BAIRRO", joinColumns = @JoinColumn(name = "CODIGO_PESSOA"),
                    inverseJoinColumns = @JoinColumn(name = "CODIGO_BAIRRO"))//anotação que indica a tabela de junção
            private Set<Bairro> enderecos; //lista de endereços que o pessoa pode ter

            
}
