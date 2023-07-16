package com.avinho.mscartoes.application;

import com.avinho.mscartoes.domain.Cartao;
import com.avinho.mscartoes.infra.CartaoRepository;
import com.avinho.mscartoes.infra.dto.CartaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    @Transactional
    public Cartao save(Cartao cartao) {
        return repository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
        var rendaBigDecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(rendaBigDecimal);
    }

    public Cartao fromDTO(CartaoRequestDTO dto) {
        return new Cartao(dto.nome(), dto.bandeira(), dto.renda(), dto.limite());
    }
}
