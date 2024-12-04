package com.bootcamp.services;


import com.bootcamp.dto.EnderecoDTO;
import com.bootcamp.dto.PessoaDTO;
import com.bootcamp.entities.Endereco;
import com.bootcamp.entities.Pessoa;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.repositories.BairroRepository;
import com.bootcamp.repositories.EnderecoRepository;
import com.bootcamp.repositories.PessoaRepository;
import com.bootcamp.mapper.PessoaMapper;
import com.bootcamp.util.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {


    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaMapper pessoaMapper;

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    public List<PessoaDTO> getAll(Long codigoPessoa, String nome, String sobrenome, Integer idade, String login, String senha, Integer status) {
        List<Pessoa> pessoas = pessoaRepository
                .findAll()
                .stream()
                .filter(a -> (codigoPessoa == null || a.getCodigoPessoa().equals(codigoPessoa))
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

    public Object getFindById(Long codigoPessoa) {
        Pessoa pessoa = pessoaRepository.findById(codigoPessoa).orElse(null);
        if (pessoa == null) {
            return new ArrayList<>();//
        }
        return pessoaMapper.toDTO(pessoa, true);//true para trazer os endereços
    }

    @Transactional
    public List<PessoaDTO> createPessoa(PessoaDTO pessoaDTO) {
        //Validações
        ValidateUtils.validateNome(pessoaDTO.getNome());//Valida se o nome é nulo ou vazio
        ValidateUtils.validateSobrenome(pessoaDTO.getSobrenome());//Valida se o sobrenome é nulo ou vazio
        ValidateUtils.validateIdade(pessoaDTO.getIdade());//Valida se a idade é nula ou menor que 0
        ValidateUtils.validateLogin(pessoaDTO.getLogin());//Valida se o login é nulo ou vazio
        ValidateUtils.validateSenha(pessoaDTO.getSenha());//Valida se a senha é nula ou vazia
        ValidateUtils.validateStatus(pessoaDTO.getStatus());//Valida se o status é nulo ou diferente de 1 ou 2
        ValidateUtils.validateEnderecosNotEmpty(pessoaDTO);//Valida se a lista de endereços é nula ou vazia


        pessoaDTO.setLogin(pessoaDTO.getLogin().trim());// Remove espaços do login



        // Valida se o login já existe
        if (pessoaRepository.existsByLogin(pessoaDTO.getLogin())) {
            throw new DesafioException("Não foi possível incluir pessoa no banco de dados. Motivo: já existe um(a) registro com o login: " + pessoaDTO.getLogin() + " no banco de dados.");
        }
        // Valida se o nome já existe

        if (pessoaRepository.existsByNome(pessoaDTO.getNome())) {
            throw new DesafioException("Não foi possível incluir pessoa no banco de dados. Motivo: já existe um(a) registro com o nome: " + pessoaDTO.getNome() + " no banco de dados.");
        }

        // Valida se o código do bairro existe. Se não existir, lança uma exceção informando o motivo
        for (EnderecoDTO endereco : pessoaDTO.getEnderecos()) {
            if (!bairroRepository.existsByCodigoBairro(endereco.getCodigoBairro())) {
                throw new DesafioException("Não foi possível incluir pessoa no banco de dados. Motivo: código do bairro " + endereco.getCodigoBairro() + " não encontrado.");
            }
        }

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
        //Validações
        validatePessoa(pessoaDTO);

        //convertendo o DTO para entidade
        Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);

        // Busca a pessoa existente no banco de dados
        Pessoa pessoaExistente = pessoaRepository.findById(pessoaDTO.getCodigoPessoa()).orElseThrow(() -> new DesafioException("Pessoa não encontrada!"));


        // Atualiza os dados da pessoa existente com os dados da pessoa atualizada
        pessoaExistente.setNome(pessoa.getNome());
        pessoaExistente.setSobrenome(pessoa.getSobrenome());
        pessoaExistente.setIdade(pessoa.getIdade());
        pessoaExistente.setLogin(pessoa.getLogin());
        pessoaExistente.setSenha(pessoa.getSenha());
        pessoaExistente.setStatus(pessoa.getStatus());


        // Atualiza os endereços
        List<Endereco> enderecosAtualizados = pessoa.getEndereco();
        List<Endereco> enderecosExistentes = pessoaExistente.getEndereco();
//
//
        // Exclui endereços que não estão na lista atualizada

        enderecosExistentes.removeIf(enderecoExistente -> enderecosAtualizados.stream().noneMatch(
                enderecoAtualizado -> enderecoAtualizado.getCodigoEndereco() != null &&
                                enderecoAtualizado.getCodigoEndereco().equals(enderecoExistente.getCodigoEndereco())
                )
        );
//
        // Adiciona ou atualiza endereços
        for (Endereco enderecoAtualizado : enderecosAtualizados) {
            if (enderecoAtualizado.getCodigoEndereco() == null) {
                // Cria novo endereço
                enderecoAtualizado.setPessoa(pessoaExistente.getCodigoPessoa());
                enderecosExistentes.add(enderecoAtualizado);
            } else {
                // Atualiza endereço existente
                for (Endereco enderecoExistente : enderecosExistentes) {
                    if (enderecoExistente.getCodigoEndereco().equals(enderecoAtualizado.getCodigoEndereco())) {
                        enderecoExistente.setNomeRua(enderecoAtualizado.getNomeRua());
                        enderecoExistente.setNumero(enderecoAtualizado.getNumero());
                        enderecoExistente.setComplemento(enderecoAtualizado.getComplemento());
                        enderecoExistente.setCep(enderecoAtualizado.getCep());
                        enderecoExistente.setBairro(enderecoAtualizado.getBairro());
                    }
                }
            }
        }

        // Salva a pessoa com os endereços atualizados
        pessoaRepository.save(pessoaExistente);

        // Retorna a lista de pessoas atualizada
        List<PessoaDTO> pessoas = getAll(null, null, null, null, null, null, null);
        return pessoas;
    }


    public void validatePessoa(PessoaDTO pessoaDTO) {
        ValidateUtils.validateNome(pessoaDTO.getNome());//Valida se o nome é nulo ou vazio
        ValidateUtils.validateSobrenome(pessoaDTO.getSobrenome());//Valida se o sobrenome é nulo ou vazio
        ValidateUtils.validateIdade(pessoaDTO.getIdade());//Valida se a idade é nula ou menor que 0
        ValidateUtils.validateLogin(pessoaDTO.getLogin());//Valida se o login é nulo ou vazio
        ValidateUtils.validateSenha(pessoaDTO.getSenha());//Valida se a senha é nula ou vazia
        ValidateUtils.validateStatus(pessoaDTO.getStatus());//Valida se o status é nulo ou diferente de 1 ou 2
        ValidateUtils.validateEnderecosNotEmpty(pessoaDTO);//Valida se a lista de endereços é nula ou vazia


        //Valida se bairro existe
        for (EnderecoDTO endereco : pessoaDTO.getEnderecos()) {
            if (!bairroRepository.existsByCodigoBairro(endereco.getCodigoBairro())) {
                throw new DesafioException("Não foi possivel alterar endereço no banco de dados. Motivo: Bairro não encontrado!");
            }
        }
        //Valida se endereço existe e se a pessoa existe no endereco
        for (EnderecoDTO endereco : pessoaDTO.getEnderecos()) {
            if (endereco.getCodigoEndereco() != null){
                if (!enderecoRepository.existsByCodigoEndereco(endereco.getCodigoEndereco())) {
                    throw new DesafioException("Não foi possivel alterar endereço no banco de dados. Motivo: Endereço não encontrado!");
                }
            }
            if (endereco.getCodigoPessoa() != null){
                if (!pessoaRepository.existsByCodigoPessoa(endereco.getCodigoPessoa())) {
                    throw new DesafioException(" Não foi possivel alterar endereço no banco de dados. Motivo: (codigoPessoa) não foi encontrada no endereco!");
                }
            }
        }

        // Valida se a pessoa existe
        if (!pessoaRepository.existsByCodigoPessoa(pessoaDTO.getCodigoPessoa())) {
            throw new DesafioException("Não foi possivel alterar pessoa no banco de dados. Motivo:Pessoa não encontrada!");
        }
    }

}
