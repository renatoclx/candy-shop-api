package com.crud.candyshopapi.domain.cliente;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Table(name = "clientes")
@Entity(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cpfCnpj;
    private String inscEstadual;
    private String cep;
    private String logradouro;
    private int numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private String estado;
    private Integer ativo;

    public Cliente(RequestCliente requestCliente) {
        this.nome = requestCliente.nome();
        this.cpfCnpj = requestCliente.cpfCnpj();
        this.cidade = requestCliente.cidade();
        this.estado = requestCliente.estado();
        this.logradouro = requestCliente.logradouro();
        this.bairro = requestCliente.bairro();
        this.inscEstadual = requestCliente.inscEstadual();
        this.cep = requestCliente.cep();
        this.numero = requestCliente.numero();
        this.complemento = requestCliente.complemento();
        this.ativo = 1;
    }
}
