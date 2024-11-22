package com.bootcamp.util;

import com.bootcamp.exceptions.DesafioException;

public class ValidateUtils {

    public static void validateNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: \"nome\" não pode ser vazio.");
        }
    }

    public static void validateSigla(String sigla) {
        if (sigla == null || sigla.trim().isEmpty()) {
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: \"sigla\" não pode ser vazia.");
        }
    }

    public static void validateStatus(Integer status) {
        if (status == null || (status != 1 && status != 2)) {
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: \"status\" deve ser 1 ou 2.");
        }
    }

    public static void validateSobrenome (String sobrenome){
        if (sobrenome == null || sobrenome.trim().isEmpty()){
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: \"sobrenome\" não pode ser vazio.");
        }
    }

    public static void validateIdade (Integer idade){
        if (idade == null || idade <= 0){
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: \"idade\" não pode ser vazio ou menor que 0.");
        }
    }

    public static void validateLogin (String login){
        if (login == null || login.trim().isEmpty()){
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: \"login\" não pode ser vazio.");
        }
    }

    public static void validateSenha (String senha){
        if (senha == null || senha.trim().isEmpty()){
            throw new DesafioException("Não foi possível realizar a operação no banco de dados. Motivo: \"senha\" não pode ser vazio.");
        }
    }

}
