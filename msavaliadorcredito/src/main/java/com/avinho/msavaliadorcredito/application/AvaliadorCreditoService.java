package com.avinho.msavaliadorcredito.application;

import com.avinho.msavaliadorcredito.application.exception.DadosClienteNotFoundException;
import com.avinho.msavaliadorcredito.application.exception.ErroComunicacaoMicroserviceException;
import com.avinho.msavaliadorcredito.domain.model.*;
import com.avinho.msavaliadorcredito.infra.clients.CartaoResourceClient;
import com.avinho.msavaliadorcredito.infra.clients.ClienteResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    public final ClienteResourceClient clienteClient;
    public final CartaoResourceClient cartaoClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException {
        try {
            ResponseEntity<Cliente> dadosClienteResponse = clienteClient.findByCpf(cpf);
            ResponseEntity<List<CartaoCliente>> dadosCartoesResponse = cartaoClient.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(dadosCartoesResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (status == HttpStatus.NOT_FOUND.value()) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroserviceException(e.getMessage(), status);
        }
    }

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException{
        try {
            ResponseEntity<Cliente> dadosClienteResponse = clienteClient.findByCpf(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartaoClient.getCartoesRendaAte(renda);

            List<Cartao> cartoes = cartoesResponse.getBody();
            var listCartoesAprovados = cartoes.stream().map(cartao -> {

                Cliente cliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.limite();
                BigDecimal idadeBD = BigDecimal.valueOf(cliente.idade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovado cartApv = new CartaoAprovado(cartao.nome(), cartao.bandeira(), limiteAprovado);
                return cartApv;
            }).toList();

            return RetornoAvaliacaoCliente
                    .builder()
                    .cartoesAprovados(listCartoesAprovados)
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (status == HttpStatus.NOT_FOUND.value()) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroserviceException(e.getMessage(), status);
        }
    }
}
