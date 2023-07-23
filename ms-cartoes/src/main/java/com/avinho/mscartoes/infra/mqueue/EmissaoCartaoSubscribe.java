package com.avinho.mscartoes.infra.mqueue;

import com.avinho.mscartoes.domain.Cartao;
import com.avinho.mscartoes.domain.ClienteCartao;
import com.avinho.mscartoes.domain.DadosSolicitacaoEmissaoCartao;
import com.avinho.mscartoes.infra.ClienteCartaoRepository;
import com.avinho.mscartoes.infra.CartaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmissaoCartaoSubscribe {

    private final CartaoRepository cartaoRepository;
    private final ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartao}")
    public void receberSolicicacaoEmissao(@Payload String payload) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();
            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.getCpf());
            clienteCartao.setLimite(dados.getLimiteLiberado());

            clienteCartaoRepository.save(clienteCartao);

        } catch (Exception e) {
            log.error("Error while saving cartao: {} ", e.getMessage());
        }
    }
}