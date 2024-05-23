package com.crud.candyshopapi.controllers;

import com.crud.candyshopapi.domain.cliente.Cliente;
import com.crud.candyshopapi.domain.cliente.ClienteRepository;
import com.crud.candyshopapi.domain.cliente.RequestCliente;
import com.crud.candyshopapi.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public class ClienteController {
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity getAllClientes() {
        var allUsers = repository.findAllByAtivoTrue();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cliente>> buscarClientes(@RequestParam String nome) {
        List<Cliente> clientes = clienteService.findClientes(nome);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity getClienteById(@PathVariable Integer id) {
        Optional<Cliente> clienteOpcional = repository.findById(id);

        if(clienteOpcional.isPresent()) {
            Cliente cliente = clienteOpcional.get();
            return ResponseEntity.ok(cliente);
        } else throw new EntityNotFoundException();
    }

    @PostMapping
    public ResponseEntity createCliente(@RequestBody @Valid RequestCliente data) {

        Cliente newCLiente = new Cliente(data);
        repository.save(newCLiente);

        return ResponseEntity.ok("Cliente inserido com Sucesso!");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateCliente(@PathVariable Integer id, @RequestBody @Valid RequestCliente data) {
        Optional<Cliente> clienteOpcional = repository.findById(id);

        if(clienteOpcional.isPresent()) {
            Cliente cliente = clienteOpcional.get();
            cliente.setNome(data.nome());
            cliente.setCpfCnpj(data.cpfCnpj());
            cliente.setInscEstadual(data.inscEstadual());
            cliente.setCep(data.cep());
            cliente.setLogradouro(data.logradouro());
            cliente.setNumero(data.numero());
            cliente.setBairro(data.bairro());
            cliente.setComplemento(data.complemento());
            cliente.setCidade(data.cidade());
            cliente.setEstado(data.estado());

            return ResponseEntity.ok(cliente);
        } else throw new EntityNotFoundException();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCliente(@PathVariable Integer id) {
        Optional<Cliente> clienteOpcional = repository.findById(id);

        if(clienteOpcional.isPresent()) {
            Cliente cliente = clienteOpcional.get();
            cliente.setAtivo(0);

            return ResponseEntity.noContent().build();
        } else throw new EntityNotFoundException();
    }
}
