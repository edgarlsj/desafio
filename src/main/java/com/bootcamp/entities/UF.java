package com.bootcamp.entities;


import com.bootcamp.records.UfRecordDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_UF")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UF {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uf_seq")
    @SequenceGenerator(name = "uf_seq", sequenceName = "UF_SEQ", allocationSize = 1)
    @Column(name = "CODIGO_UF")
    private Long codigoUF;

    @Column(name = "SIGLA")
    private String sigla;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "STATUS")
    private Integer status;


    public UF(Long codigoUF) {
        this.codigoUF = codigoUF;
    }

    public UF (UfRecordDto dto){
        this.sigla = dto.sigla().toUpperCase();
        this.nome = dto.nome().toUpperCase();
        this.status = dto.status();
    }


}
