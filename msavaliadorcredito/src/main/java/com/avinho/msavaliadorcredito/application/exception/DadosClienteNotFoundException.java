package com.avinho.msavaliadorcredito.application.exception;

public class DadosClienteNotFoundException extends Exception {
    public DadosClienteNotFoundException() {
        super("Cliente not found");
    }
}
