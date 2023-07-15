package com.avinho.msclientes.application;

import com.avinho.msclientes.application.dto.ClienteRequestDTO;
import com.avinho.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteResource {

    private final ClienteService service;

    @GetMapping
    public String status() {
        log.info("Status => msclientes");
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ClienteRequestDTO request) {
        Cliente cliente = service.fromDTO(request);
        service.insert(cliente);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Cliente> findByCpf(@RequestParam("cpf") String cpf) {
        Optional<Cliente> cliente = service.findByCpf(cpf);
        return cliente.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }



}
