package com.bootcamp.repositories;

import com.bootcamp.entities.Municipio;
import com.bootcamp.entities.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    boolean existsByNome(String nome);//cria um método que verifica se existe um município com o nome informado

    boolean existsByCodigoMunicipio(Long codigoMunicipio);//cria um método que verifica se existe um município com o código informado

    boolean existsByNomeAndUf_CodigoUF(String nome, Long codigoUF);//cria um método que verifica se existe um município com o nome e código da UF informados


    @Query("SELECT m FROM Municipio m WHERE " +
            "(:codigoMunicipio IS NULL OR m.codigoMunicipio = :codigoMunicipio) AND " +
            "(:codigoUF IS NULL OR m.uf.codigoUF = :codigoUF) AND " +
            "(:nome IS NULL OR LOWER(m.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:status IS NULL OR m.status = :status)")
    List<Municipio> findByFilters(@Param("codigoMunicipio") Optional<Long> codigoMunicipio,
                                  @Param("codigoUF") Optional<Long> codigoUF,
                                  @Param("nome") Optional<String> nome,
                                  @Param("status") Optional<Integer> status);


}
