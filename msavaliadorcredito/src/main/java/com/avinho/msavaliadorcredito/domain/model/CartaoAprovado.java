package com.avinho.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;

public record CartaoAprovado(String cartao, String bandeira, BigDecimal limiteAprovado) {
}
