package com.crud.candyshopapi.domain.cliente;


public record RequestCliente(int id, String nome, String cpfCnpj, String inscEstadual, String cep, String logradouro, int numero,
                             String bairro, String complemento, String cidade, String estado) {
}
