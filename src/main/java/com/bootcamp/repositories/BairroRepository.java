package com.bootcamp.repositories;

import com.bootcamp.entities.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BairroRepository extends JpaRepository<Bairro, Long> {

    boolean existsByNome(String nome);

    boolean existsByCodigoBairro(Long codigoBairro);

    boolean existsByNomeAndMunicipio_CodigoMunicipio(String nome, Long codigoMunicipio);

    @Query("SELECT b FROM Bairro b WHERE " +
            "(:codigoBairro IS NULL OR b.codigoBairro = :codigoBairro) AND " +
            "(:codigoMunicipio IS NULL OR b.municipio.codigoMunicipio = :codigoMunicipio) AND " +
            "(:nome IS NULL OR LOWER(b.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:status IS NULL OR b.status = :status)")
    List<Bairro> findByFilters(@Param("codigoBairro") Optional<Long> codigoBairro,
                               @Param("codigoMunicipio") Optional<Long> codigoMunicipio,
                               @Param("nome") Optional<String> nome,
                               @Param("status") Optional<Integer> status);





}
