package com.bootcamp.repositories;

import com.bootcamp.entities.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    boolean existsByNome(String nome);//cria um método que verifica se existe um município com o nome informado

}
