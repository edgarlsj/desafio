package com.bootcamp.exceptions;

public class ErrorResponseDesafio {

    private String mensagem;
    private int status;

    public ErrorResponseDesafio(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public int getStatus() {
        return status;
    }
}

