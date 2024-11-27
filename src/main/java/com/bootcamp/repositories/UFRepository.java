package com.bootcamp.repositories;

import com.bootcamp.entities.UF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UFRepository extends JpaRepository<UF, Long> {

    boolean existsBySigla(String sigla);//cria um método que verifica se existe uma UF com a sigla informada
    boolean existsByNome(String nome);//cria um método que verifica se existe uma UF com o nome informado

    boolean existsByCodigoUF(Long codigoUF);//cria um método que verifica se existe uma UF com o código informado

    Optional<UF> findBySigla(String sigla);

    Optional<UF> findByNome(String nome);

}
