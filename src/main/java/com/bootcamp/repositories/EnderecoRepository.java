package com.bootcamp.repositories;

import com.bootcamp.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    boolean existsByCodigoEndereco(Long codigoEndereco);






}
