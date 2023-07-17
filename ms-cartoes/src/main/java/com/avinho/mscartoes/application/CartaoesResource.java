package com.avinho.mscartoes.application;

import com.avinho.mscartoes.domain.Cartao;
import com.avinho.mscartoes.domain.ClienteCartao;
import com.avinho.mscartoes.infra.dto.CartaoRequestDTO;
import com.avinho.mscartoes.infra.dto.CartoesPorClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartaoesResource {

    private final CartaoService cartaoService;
    private final CartaoClienteService cartaoClienteService;

    @GetMapping
    public String status() {
        return "Status => Running!!";
    }

    @PostMapping
    public ResponseEntity<Void> cadastra(@RequestBody CartaoRequestDTO data) {
        Cartao cartao = cartaoService.fromDTO(data);
        cartaoService.save(cartao);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        List<Cartao> cartoes = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok().body(cartoes);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteDTO>> getCartoesByCliente(@RequestParam("cpf") String cpf) {
        List<ClienteCartao> cartoes = cartaoClienteService.listCartoesByCpf(cpf);
        List<CartoesPorClienteDTO> listCartoes = cartoes.stream().map(CartoesPorClienteDTO::fromModel).collect(Collectors.toList());

        return ResponseEntity.ok().body(listCartoes);
    }
}
