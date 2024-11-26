package com.bootcamp.repositories;

import com.bootcamp.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    boolean existsByLogin(String login);//cria um método que verifica se existe uma pessoa com o login informado

    boolean existsByNome(String nome);//cria um método que verifica se existe uma pessoa com o nome informado

    boolean existsBySobrenome(String sobrenome);//cria um método que verifica se existe uma pessoa com o sobrenome informado

    boolean existsByCodigoPessoa(Long codigoPessoa);//cria um método que verifica se existe uma pessoa com o código informado
}
