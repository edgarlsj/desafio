package com.bootcamp.util;

import com.bootcamp.dto.EnderecoDTO;
import com.bootcamp.dto.PessoaDTO;
import com.bootcamp.exceptions.DesafioException;

public class ValidateUtils {

    public static void validateNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: o campo (nome) é obrigatório.");
        }
    }

    public static void validateSigla(String sigla) {
        if (sigla == null || sigla.trim().isEmpty() || sigla.length() != 2 || !sigla.matches("[a-zA-Z]+")) {
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: (sigla) é obrigatório, deve ter exatamente 2 caracteres e deve ser texto.");
        }
    }

    public static void validateStatus(Integer status) {
        if (status == null || (status != 1 && status != 2)) {
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: (status) deve ser 1 ou 2 ou está vazio.");
        }
    }

    public static void validateSobrenome (String sobrenome){
        if (sobrenome == null || sobrenome.trim().isEmpty()){
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: (sobrenome) é obrigatório.");
        }
    }

    public static void validateIdade (Integer idade){
        if (idade == null || idade <= 0){
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: (idade) não pode ser vazio ou menor que 0.");
        }
    }

    public static void validateLogin (String login){
        if (login == null || login.trim().isEmpty()){
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: (login) é obrigatório.");
        }
    }

    public static void validateSenha (String senha){
        if (senha == null || senha.trim().isEmpty()){
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: (senha) é obrigatório.");
        }
    }


    public static void validateEnderecosNotEmpty(PessoaDTO pessoaDTO) {
        if (pessoaDTO.getEnderecos() == null || pessoaDTO.getEnderecos().isEmpty()) {
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: pelo menos um endereço é obrigatório.");
        }
    }

    public static void validatecodigoUF(Long codigoUF) {
        if (codigoUF == null) {
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: (codigoUF) é obrigatório.");
        }
    }

    public static void validateNomeRua (String nomeRua){
        if (nomeRua == null || nomeRua.trim().isEmpty()){
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: (nomeRua) é obrigatório.");
        }
    }

    public static void validateNumero (Integer numero){
        if (numero == null || numero <= 0){
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: (numero) não pode ser vazio ou menor que 0.");
        }
    }

    public static void  validateCep (String cep){
        if (cep == null || cep.trim().isEmpty()){
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: (cep) é obrigatório.");
        }
    }

    public static void validateComplemento (String complemento){
        if (complemento == null || complemento.trim().isEmpty()){
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: (complemento) é obrigatório.");
        }
    }

    public static void validateCodigoPessoa(Long codigoPessoa) {
        if (codigoPessoa == null) {
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: (codigoPessoa) é obrigatório.");
        }
    }

    public static void validateEndereco(PessoaDTO pessoaDTO) {
        for (EnderecoDTO endereco : pessoaDTO.getEnderecos()) {
            validateNomeRua(endereco.getNomeRua());
            validateNumero(endereco.getNumero());
            validateCep(endereco.getCep());
            validateComplemento(endereco.getComplemento());
            validateCodigoPessoa(endereco.getCodigoPessoa());

        }
    }








}
