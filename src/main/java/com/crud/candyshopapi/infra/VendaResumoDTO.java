package com.crud.candyshopapi.infra;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaResumoDTO {

    private Long vendaId;
    private Long clienteId;
    private String nome;
    private Double precoTotalProduto;

    public VendaResumoDTO(Long vendaId, Long clienteId, String nome, Double precoTotalProduto) {
        this.vendaId = vendaId;
        this.clienteId = clienteId;
        this.nome = nome;
        this.precoTotalProduto = precoTotalProduto;
    }

}
