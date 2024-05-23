package com.crud.candyshopapi.service;

import com.crud.candyshopapi.domain.cliente.Cliente;
import com.crud.candyshopapi.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public List<Cliente> findClientes(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }
}
