package com.avinho.msavaliadorcredito.infra.clients;

import com.avinho.msavaliadorcredito.domain.model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteResourceClient {
    @GetMapping(params = "cpf")
    ResponseEntity<Cliente> findByCpf(@RequestParam("cpf") String cpf);
}
