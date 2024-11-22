package com.bootcamp.repositories;

import com.bootcamp.entities.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<Bairro, Long> {

    boolean existsByNome(String nome);

    boolean existsByCodigoBairro(Long codigoBairro);
}
