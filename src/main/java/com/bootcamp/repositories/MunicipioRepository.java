package com.bootcamp.repositories;

import com.bootcamp.entities.Municipio;
import com.bootcamp.entities.UF;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    boolean existsByNome(String nome);//cria um método que verifica se existe um município com o nome informado

    boolean existsByCodigoMunicipio(Long codigoMunicipio);//cria um método que verifica se existe um município com o código informado

    boolean existsByNomeAndUf_CodigoUF(String nome, Long codigoUF);//cria um método que verifica se existe um município com o nome e código da UF informados

}
