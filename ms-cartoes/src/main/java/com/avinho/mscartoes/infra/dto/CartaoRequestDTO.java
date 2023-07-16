package com.avinho.mscartoes.infra.dto;

import com.avinho.mscartoes.domain.BandeiraCartao;

import java.math.BigDecimal;

public record CartaoRequestDTO(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limite) {
}
