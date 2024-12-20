package com.bootcamp.repositories;

import com.bootcamp.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    boolean existsByCodigoEndereco(Long codigoEndereco);

    @Query("SELECT e FROM Endereco e WHERE e.pessoa = :codigoPessoa")
    List<Endereco> findByCodigoPessoa(Long codigoPessoa);


    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Endereco e WHERE e.codigoEndereco = :codigoEndereco AND e.pessoa <> :pessoa")
    boolean existsByCodigoEnderecoAndPessoa_PessoaNot(@Param("codigoEndereco") Long codigoEndereco, @Param("pessoa") Long pessoa);
}







