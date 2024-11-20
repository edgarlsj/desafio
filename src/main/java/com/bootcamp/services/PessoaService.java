package com.bootcamp.services;


import com.bootcamp.dto.PessoaDTO;
import com.bootcamp.entities.Pessoa;
import com.bootcamp.repositories.PessoaRepository;
import com.bootcamp.mapper.PessoaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {



    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaMapper pessoaMapper;



    public List<PessoaDTO> getAll(Long codigoPessoa, String nome, String sobrenome, Integer idade, String login, String senha, Integer status) {
        List<Pessoa> pessoas = pessoaRepository
                .findAll()
                .stream()
                .filter(a -> (codigoPessoa == null || a.getCodigoPessoa().equals(codigoPessoa) )
                        && (nome == null || a.getNome().equalsIgnoreCase(nome))
                        && (sobrenome == null || a.getSobrenome().equalsIgnoreCase(sobrenome))
                        && (idade == null || a.getIdade() == idade)
                        && (login == null || a.getLogin().equalsIgnoreCase(login))
                        && (senha == null || a.getSenha().equalsIgnoreCase(senha))
                        && (status == null || a.getStatus() == status))
                .collect(Collectors.toList());


        return pessoas.stream()
                .map(pessoa -> pessoaMapper.toDTO(pessoa, false))//false para não trazer os endereços lista vazia
                .collect(Collectors
                        .toList());
    }

    public PessoaDTO getFindById (Long codigoPessoa){
        Pessoa pessoa = pessoaRepository.findById(codigoPessoa).orElse(null);
        return pessoaMapper.toDTO(pessoa, true);//true para trazer os endereços
    }

}
