package com.bootcamp.repositories;

import com.bootcamp.entities.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BairroRepository extends JpaRepository<Bairro, Long> {

    boolean existsByNome(String nome);

    boolean existsByCodigoBairro(Long codigoBairro);

    boolean existsByNomeAndMunicipio_CodigoMunicipio(String nome, Long codigoMunicipio);





}
