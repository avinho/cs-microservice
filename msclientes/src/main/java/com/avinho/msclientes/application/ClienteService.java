package com.avinho.msclientes.application;

import com.avinho.msclientes.application.dto.ClienteRequestDTO;
import com.avinho.msclientes.domain.Cliente;
import com.avinho.msclientes.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional
    public Cliente insert(Cliente data) {
        return repository.save(data);
    }

    public Optional<Cliente> findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente fromDTO (ClienteRequestDTO dto) {
        return new Cliente(dto.nome(), dto.cpf(), dto.idade());
    }
}
