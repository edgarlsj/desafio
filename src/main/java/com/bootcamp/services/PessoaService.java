package com.bootcamp.services;


import com.bootcamp.dto.PessoaDTO;
import com.bootcamp.entities.Endereco;
import com.bootcamp.entities.Pessoa;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.repositories.PessoaRepository;
import com.bootcamp.mapper.PessoaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Pessoa pessoa = pessoaRepository.findById(codigoPessoa).orElseThrow(() -> new DesafioException("Não foi possível consultar pessoa no banco de dados."));
        return pessoaMapper.toDTO(pessoa, true);//true para trazer os endereços
    }

    @Transactional
    public List<PessoaDTO> createPessoa(PessoaDTO pessoaDTO) {
        //converte o DTO para entidade
        Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);

        //salva a entidade no banco de dados
        pessoaRepository.save(pessoa);

        // Define o codigoPessoa gerado em cada Endereco
        for (Endereco endereco : pessoa.getEndereco()) {
            endereco.setPessoa(pessoa.getCodigoPessoa());
        }

        // Salva a Pessoa novamente com os Enderecos atualizados
         pessoaRepository.save(pessoa);


        List<PessoaDTO> pessoas = getAll(null, null, null, null, null, null, null);
        return pessoas;
    }

    @Transactional
    public List<PessoaDTO> updatePessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);
        pessoaRepository.save(pessoa);
        List<PessoaDTO> pessoas = getAll(null, null, null, null, null, null, null);
        return pessoas;
    }

}
