package com.avinho.mscartoes.infra.dto;

import com.avinho.mscartoes.domain.ClienteCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CartoesPorClienteDTO {

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartoesPorClienteDTO fromModel(ClienteCartao cartao) {
        return new CartoesPorClienteDTO(
                cartao.getCartao().getNome(),
                cartao.getCartao().getBandeira().toString(),
                cartao.getLimite()
        );
    }
}
