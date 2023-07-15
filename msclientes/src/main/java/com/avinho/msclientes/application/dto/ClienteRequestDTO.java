package com.avinho.msclientes.application.dto;

import ch.qos.logback.core.net.server.Client;

public record ClienteRequestDTO(String nome, String cpf, Integer idade) {
}
