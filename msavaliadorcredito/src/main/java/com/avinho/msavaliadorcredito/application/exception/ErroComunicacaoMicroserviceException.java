package com.avinho.msavaliadorcredito.application.exception;

import lombok.Getter;

public class ErroComunicacaoMicroserviceException extends Exception {

    @Getter
    private Integer errorCode;
    public ErroComunicacaoMicroserviceException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
