package com.bootcamp.repositories;

import com.bootcamp.entities.Endereco;
import com.bootcamp.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    boolean existsByLogin(String login);//cria um método que verifica se existe uma pessoa com o login informado

    boolean existsByNome(String nome);//cria um método que verifica se existe uma pessoa com o nome informado

    boolean existsBySobrenome(String sobrenome);//cria um método que verifica se existe uma pessoa com o sobrenome informado

    boolean existsByCodigoPessoa(Long codigoPessoa);//cria um método que verifica se existe uma pessoa com o código informado



    @Query("SELECT p FROM Pessoa p WHERE (:codigoPessoa IS NULL OR p.codigoPessoa = :codigoPessoa) AND (:login IS NULL OR p.login = :login) AND (:status IS NULL OR p.status = :status)")
    List<Pessoa> findByFilters(@Param("codigoPessoa") Optional<Long> codigoPessoa, @Param("login") Optional<String> login, @Param("status") Optional<Integer> status);

    Pessoa findByCodigoPessoa(Long codigoPessoa);





}
