package com.avinho.mscartoes.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class Cartao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;

    private BigDecimal renda;

    private BigDecimal limite;

    public Cartao(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limite) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limite = limite;
    }
}
