package com.bootcamp.repositories;

import com.bootcamp.entities.UF;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UFRepository extends JpaRepository<UF, Long> {

    boolean existsBySigla(String sigla);//cria um método que verifica se existe uma UF com a sigla informada
    boolean existsByNome(String nome);//cria um método que verifica se existe uma UF com o nome informado
}
