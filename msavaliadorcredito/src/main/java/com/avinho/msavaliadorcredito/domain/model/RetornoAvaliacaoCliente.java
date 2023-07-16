package com.avinho.msavaliadorcredito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetornoAvaliacaoCliente {
    private List<CartaoAprovado> cartoesAprovados;
}
