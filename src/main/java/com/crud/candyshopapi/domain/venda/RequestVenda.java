package com.crud.candyshopapi.domain.venda;

import com.crud.candyshopapi.domain.produto.Produto;
import com.crud.candyshopapi.domain.cliente.Cliente;

public record RequestVenda(int id, Cliente cliente, Produto produto, int quantidade, float valorUnitario,
                                    float valorTotal) {
}
