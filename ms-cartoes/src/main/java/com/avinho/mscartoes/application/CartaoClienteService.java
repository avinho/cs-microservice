package com.avinho.mscartoes.application;

import com.avinho.mscartoes.domain.CartaoCliente;
import com.avinho.mscartoes.infra.CartaoClienteRepository;
import com.avinho.mscartoes.infra.dto.CartoesPorClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoClienteService {

    private final CartaoClienteRepository repository;

    public List<CartaoCliente> listCartoesByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public CartoesPorClienteDTO fromDomain(CartaoCliente cartao) {
        return new CartoesPorClienteDTO(cartao);
    }
}
