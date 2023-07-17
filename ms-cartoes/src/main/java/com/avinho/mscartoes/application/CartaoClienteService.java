package com.avinho.mscartoes.application;

import com.avinho.mscartoes.domain.ClienteCartao;
import com.avinho.mscartoes.infra.ClienteCartaoRepository;
import com.avinho.mscartoes.infra.dto.CartoesPorClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoClienteService {

    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listCartoesByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
