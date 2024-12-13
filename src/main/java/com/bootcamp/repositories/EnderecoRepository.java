package com.bootcamp.repositories;

import com.bootcamp.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    boolean existsByCodigoEndereco(Long codigoEndereco);

    @Query("SELECT e FROM Endereco e WHERE e.pessoa = :codigoPessoa")
    List<Endereco> findByCodigoPessoa(Long codigoPessoa);
}







